package com.example.springboot2_01.config;

import com.example.springboot2_01.bean.Pet;
import com.example.springboot2_01.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 1.配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 
 */
@Configuration //说明这是一个配置类，配置文件
public class MyConfig {

    @Bean //给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
    public User user01(){
         return new User("zhangsan",18);
    }
    @Bean
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }

}
