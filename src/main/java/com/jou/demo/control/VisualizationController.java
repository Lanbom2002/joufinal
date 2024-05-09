package com.jou.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisualizationController {
    @RequestMapping("/visualization")
    public String say(){
        return "html/visualization.html";
    }
}
