package com.aurora.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//2.添加feign接口和方法

/*
name 指定调用rest接口所对应的服务名
path 指定调用rest接口所在的StockController指定的@RequestMapping
 */

@FeignClient(value = "stock-nacos",path="/stock",fallback = StockFeignServiceFallback.class)
public interface StockFeignService {

    @RequestMapping("/reduct")
    public String reduct2();

}


