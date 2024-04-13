import django
import os
import sqlite3

import numpy as np
import tensorflow as tf
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras.callbacks import TensorBoard
import datetime

# 设置TensorBoard日志目录
log_dir = "logs/fit/" + datetime.datetime.now().strftime("%Y%m%d-%H%M%S")
tensorboard_callback = TensorBoard(log_dir=log_dir, histogram_freq=1)

# 提取数据
data = [(msg.question, msg.answer) for msg in Message.objects.all()]

# 分离问题和答案
questions, answers = zip(*data)
questions = list(questions)
answers = ['<start> ' + a + ' <end>' for a in answers]  # 添加起始和结束标记

# 文本分词和转换为整数序列
tokenizer = Tokenizer(filters='')
tokenizer.fit_on_texts(questions + answers)
question_seq = tokenizer.texts_to_sequences(questions)
answer_seq = tokenizer.texts_to_sequences(answers)

# 填充序列以保持一致的长度
max_length = max(max(len(x) for x in question_seq), max(len(x) for x in answer_seq))
question_seq = pad_sequences(question_seq, maxlen=max_length, padding='post')
answer_seq = pad_sequences(answer_seq, maxlen=max_length + 1, padding='post')  # +1 for <end> token

# 转换为Tensor
question_seq = tf.convert_to_tensor(question_seq)
answer_seq = tf.convert_to_tensor(answer_seq)

from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Embedding, MultiHeadAttention, Dense, LayerNormalization, Dropout

def transformer_encoder(inputs, embed_dim, num_heads, ff_dim, rate=0.1):
    # Multi-head self-attention
    attention = MultiHeadAttention(num_heads=num_heads, key_dim=embed_dim)(inputs, inputs)
    attention = Dropout(rate)(attention)
    attention = LayerNormalization(epsilon=1e-6)(inputs + attention)

    # Feed-forward network
    outputs = Dense(ff_dim, activation='relu')(attention)
    outputs = Dense(embed_dim)(outputs)
    outputs = Dropout(rate)(outputs)
    outputs = LayerNormalization(epsilon=1e-6)(attention + outputs)
    return outputs

# Input layers
input_question = Input(shape=(max_length,))
input_answer = Input(shape=(max_length,))

# Embeddings
embedding_layer = Embedding(input_dim=len(tokenizer.word_index) + 1, output_dim=256)
question_embeddings = embedding_layer(input_question)
answer_embeddings = embedding_layer(input_answer)

# Encoder and Decoder Stack
encoder_outputs = transformer_encoder(question_embeddings, 256, 8, 512)
decoder_outputs = transformer_encoder(answer_embeddings, 256, 8, 512)

# Output layer
outputs = Dense(len(tokenizer.word_index) + 1, activation='softmax')(decoder_outputs)

# Define model
model = Model(inputs=[input_question, input_answer], outputs=outputs)
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
model.summary()

history = model.fit(
    [question_seq, answer_seq[:, :-1]],  # 输入数据
    answer_seq[:, 1:],                  # 目标数据
    batch_size=32,
    epochs=10,
    callbacks=[tensorboard_callback]    # 添加TensorBoard回调
)


def generate_answer(model, question):
    question_seq = tokenizer.texts_to_sequences([question])
    question_seq = pad_sequences(question_seq, maxlen=max_length, padding='post')
    question_seq = tf.convert_to_tensor(question_seq)

    # <start> token
    answer_seq = [tokenizer.word_index['<start>']]
    for _ in range(max_length):
        answer_pred = model.predict([question_seq])
