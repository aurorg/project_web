package com.aurora;

import com.aurora.config.SpringConfig;
import com.aurora.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class App {
    public static void main(String[] args){
        AnnotationConfigApplicationContext  ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(ctx.getBean(UserController.class));;
    }
}
