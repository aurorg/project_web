package com.example.springboot2_01;

import com.example.springboot2_01.bean.Pet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Springboot201Application {

    public static void main(String[] args) {
        //1.返回ioc容器
        ConfigurableApplicationContext run =SpringApplication.run(Springboot201Application.class, args);

        //2.查看容器中的组件
        String[] names =run.getBeanDefinitionNames();
        for(String name :names){
            System.out.println(name);
        }

        //3.从容器中获取组件
        Pet tomcat01=run.getBean("tomcatPet", Pet.class);
        Pet tomcat02=run.getBean("tomcatPet", Pet.class);

        System.out.println("组件："+(tomcat01==tomcat02));

    }

}
