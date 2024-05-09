package com.jou.demo.service.Impl;

import com.jou.demo.service.AnswerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnswerServiceImpl implements AnswerService {

    // Database item list



    @Override
    public Map<String,String> submit(String content, List<String> itemList) {
        // String jsonData = "{\"name\":\"John\", \"age\":30}";
        // Change later

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
        // 定义一个空列表用于返回
        Map<String,String> reMap=new HashMap<>();
        for (int i=0;i<jsonObject.size();i++){
            String num=String.valueOf(i);
            // String answ=jsonObject.get(num).getAsString();
            // System.out.println(num+":"+answ);
            String json_item_value = String.valueOf(jsonObject.get(itemList.get(i)));
            System.out.println(itemList.get(i)+":"+json_item_value);
            // 列表增添一个tuple，包含itemList.get(i)和json_item_value
            reMap.put(itemList.get(i), json_item_value);

        }


        return reMap;
    }
}


