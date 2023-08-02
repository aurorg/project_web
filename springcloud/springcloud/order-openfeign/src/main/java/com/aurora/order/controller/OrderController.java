package com.aurora.order.controller;

import com.aurora.order.feign.ProductFeignService;
import com.aurora.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功！");
        String s=stockFeignService.reduct();


        String p= productFeignService.get(1);
        return "hello Feign!" +s +p  ;
    }

}
