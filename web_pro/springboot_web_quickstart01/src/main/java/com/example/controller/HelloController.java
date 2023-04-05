package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//请求处理类
@RestController
public class HelloController {

    @RequestMapping("/hellos")
    public String hellos(){
        System.out.println("hellos!!!");
        return "hellos";
    }
}
