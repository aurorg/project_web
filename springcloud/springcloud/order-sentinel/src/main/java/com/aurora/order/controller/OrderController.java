package com.aurora.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功！");
        return "hello" ;
    }

    @RequestMapping("/flow")
    @SentinelResource(value="flow",blockHandler = "flowBlockHandler")
    public String flow() throws InterruptedException{
     return "正常访问";
    }

    public String flowBlockHandler(BlockException e){
        e.printStackTrace();
        return "流控！";
    }

    @RequestMapping("/flowThread")
    @SentinelResource(value="flowThread",blockHandler = "flowBlockHandler")
    public String flowThread() throws InterruptedException{
        TimeUnit.SECONDS.sleep(5);
        return "正常访问";
    }

}
