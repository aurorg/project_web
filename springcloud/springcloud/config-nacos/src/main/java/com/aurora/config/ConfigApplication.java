package com.aurora.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext=SpringApplication.run(ConfigApplication.class,args);
        String userName =applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("user name:" + userName +"; age: " + userAge);

    }

}
