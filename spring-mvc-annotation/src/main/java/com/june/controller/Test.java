package com.june.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
    public Test() {
        System.out.println("创建了");
    }

    @RequestMapping("/test")
    public String Test(Model model){
        System.out.println("调用了");
        model.addAttribute("msg","注入值！！！");
        return  "test";
    }
}
