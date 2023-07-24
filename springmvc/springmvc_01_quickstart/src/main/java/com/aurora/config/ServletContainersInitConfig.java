package com.aurora.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;


//4.定义一个servlet容器启动的配置类，在里面加载spring的配置
//web容器配置类
//public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
//    //加载springmvc配置类，产生springmvc容器（本质还是spring容器）
//    protected WebApplicationContext createServletApplicationContext() {
//        //初始化WebApplicationContext对象
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        //加载指定配置类
//        ctx.register(SpringMvcConfig.class);
//        return ctx;
//    }
//
//    //设置由springmvc控制器处理的请求映射路径
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    //加载spring配置类
//    protected WebApplicationContext createRootApplicationContext() {
//        return null;
//    }
//}

public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer{
    //加载springmvc容器的配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }
    //设置哪些请求归属springmvc的处理
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //加载spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
