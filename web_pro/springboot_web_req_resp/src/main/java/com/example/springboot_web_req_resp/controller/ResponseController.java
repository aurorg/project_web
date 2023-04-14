package com.example.springboot_web_req_resp.controller;


import com.example.springboot_web_req_resp.pojo.Address;
import com.example.springboot_web_req_resp.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//测试响应数据
//@RestController
//public class ResponseController {
//
//    @RequestMapping("/hello")
//    public String hello(){
//        System.out.println("hello world!");
//        return "hello";
//    }
//
//    @RequestMapping("/getAddr")
//    public Address getAddr(){
//        Address addr= new Address();
//        addr.setProvince("陕西");
//        addr.setCity("安康");
//        return addr;
//
//    }
//
//    @RequestMapping("/listAddr")
//    public List<Address> listAddr() {
//        List<Address> list = new ArrayList<>();
//
//        Address addr = new Address();
//        addr.setProvince("江苏");
//        addr.setCity("南京");
//
//        Address addr2 = new Address();
//        addr2.setProvince("北京");
//        addr2.setCity("北京");
//
//        list.add(addr);
//        list.add(addr2);
//        return list;
//
//    }


@RestController
public class ResponseController {

    @RequestMapping("/hello")
    public Result hello(){
        System.out.println("hello world!");
        //return new Result(1,"success","hello!!!");
        return Result.success("hello!!!");
    }

    @RequestMapping("/getAddr")
    public Result getAddr(){
        Address addr= new Address();
        addr.setProvince("陕西");
        addr.setCity("安康");
        return Result.success(addr);

    }

    @RequestMapping("/listAddr")
    public Result listAddr() {
        List<Address> list = new ArrayList<>();

        Address addr = new Address();
        addr.setProvince("江苏");
        addr.setCity("南京");

        Address addr2 = new Address();
        addr2.setProvince("北京");
        addr2.setCity("北京");

        list.add(addr);
        list.add(addr2);
        return Result.success(list);

    }

}
