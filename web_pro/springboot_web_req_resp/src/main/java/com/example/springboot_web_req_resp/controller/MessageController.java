package com.example.springboot_web_req_resp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {
    @PostMapping("/send-message")
    public Map<String, String> sendMessage(@RequestBody Map<String, String> message) {
        String responseMessage = "您发送的消息是：" + message.get("message");
        Map<String, String> response = new HashMap<>();
        response.put("response", responseMessage);
        return response;
    }
}