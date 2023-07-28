package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    //处理其他异常
    @ExceptionHandler(Exception.class) //定义处理哪一种异常
    public Result doException(Exception ex){
       return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，稍后再试");
    }


    //处理系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    //处理业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessExceptionException(BusinessException ex){

        return new Result(ex.getCode(),null,ex.getMessage());
    }
}
