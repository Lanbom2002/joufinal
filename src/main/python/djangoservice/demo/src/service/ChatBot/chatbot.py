from openai import OpenAI
import os

client = OpenAI()

# 定义获取信息的函数
def get_disease_info(disease_name):

    response = client.chat.completions.create(
        model="gpt-3.5-turbo",  # 确认模型名称
        messages=[
            {"role": "system", "content": "你是一个医学信息助手。"},
            {"role": "user", "content": f"请描述{disease_name}的症状、治疗方法和注意事项。响应不超过 300 个tokens,回复应该是中文"}
        ],
        max_tokens=300,
        temperature=0,
    )

    return response.choices[0].message.content.strip()


answer = get_disease_info('fever')
