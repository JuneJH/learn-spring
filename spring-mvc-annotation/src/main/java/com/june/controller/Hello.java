package com.june.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
    public Hello() {
        System.out.println("创建了");
    }

    @RequestMapping("/test")
    public String Test(){
        System.out.println("调用了");
        return  "test";
    }
}
