package com.itheima.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class) //定义处理哪一种异常
    public Result doException(Exception ex){
       return new Result(000,null,"异常！！！！");
    }
}
