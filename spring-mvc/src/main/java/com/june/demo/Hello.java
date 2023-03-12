package com.june.demo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //ModelAndView 得到Model和视图对象
        ModelAndView mv = new ModelAndView();
        //注入值
        mv.addObject("msg","注入jsp的值！！！！");
        //注入视图
        mv.setViewName("hello");
        // 响应
        return mv;
    }
}
