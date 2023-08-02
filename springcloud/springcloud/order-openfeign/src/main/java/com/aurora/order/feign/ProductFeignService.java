package com.aurora.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="product-service",path="/product")
public interface ProductFeignService {

    @RequestMapping("/{id}")
    public String get(@PathVariable("id") Integer id);

}
