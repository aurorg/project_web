package com.aurora.order.controller;

import com.aurora.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeignService stockFeignService;



    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功！");
        String s=stockFeignService.reduct2();


        return "hello Feign!" +s  ;
    }

}
