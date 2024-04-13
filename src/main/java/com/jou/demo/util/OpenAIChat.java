package com.jou.demo.util;

import okhttp3.*;
import com.google.gson.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OpenAIChat {
    private static final String OPENAI_API_KEY = "你的OpenAI API密钥";
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static String createThread(String content) {
        // 创建请求 URL 和请求体
        String url = "https://api.openai.com/v1/threads";
        JsonObject payload = new JsonObject();
        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", content);
        messages.add(message);
        payload.add("messages", messages);

        String token = OPENAI_API_KEY;
        String encodedToken = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));

        // 发送 HTTP POST 请求
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + encodedToken)
                .post(RequestBody.create(payload.toString(), MediaType.parse("application/json")))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // 解析响应体
            JsonObject responseJson = gson.fromJson(response.body().string(), JsonObject.class);
            return responseJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 主程序入口，测试用
    public static void main(String[] args) {
        String content = "你的输入内容";
        String response = createThread(content);
        System.out.println(response);
    }
}