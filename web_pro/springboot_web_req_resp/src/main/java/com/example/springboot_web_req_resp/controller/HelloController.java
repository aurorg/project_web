package com.example.springboot_web_req_resp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String hello(@RequestParam String name) {
        System.out.println(name);
        return "Hello, " + name + "!";
    }
}