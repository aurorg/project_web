package com.example.springboot_web_req_resp.controller;


//测试请求参数接受

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController {

    @RequestMapping("/simpleParam")
    public String simpleParam(HttpServletRequest request){

        //获取请求参数
        String name =request.getParameter("name");
        String ageStr=request.getParameter("age");

        int age=Integer.parseInt(ageStr);
        System.out.println(name + ":" +age);

        return "OK";
    }
}
