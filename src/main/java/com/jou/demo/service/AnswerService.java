package com.jou.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AnswerService {
    //提交
    Map<String,String>  submit(String content, List<String> itemList);
}
