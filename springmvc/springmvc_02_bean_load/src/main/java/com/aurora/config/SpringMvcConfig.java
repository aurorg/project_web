package com.aurora.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//springmvc配置类，本质上还是一个spring配置类
//3.创建springmvc的配置文件，加载controller对应的bean
@Configuration
@ComponentScan("com.aurora.controller")
public class SpringMvcConfig {
}
