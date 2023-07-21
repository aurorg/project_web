package com.example.springboot2_01;

import com.example.springboot2_01.bean.Pet;
import com.example.springboot2_01.bean.User;
import com.example.springboot2_01.config.MyConfig;
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

        //4.com.example.springboot2_01.config.MyConfig$$EnhancerBySpringCGLIB$$b0eeea52@1eaa821d
        MyConfig bean =run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。
        // SpringBoot总会检查这个组件是否在容器中有。
        //保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);


        User user01 = run.getBean("user01", User.class);
        Pet tomcatPet = run.getBean("tomcatPet", Pet.class);

        System.out.println("用户的宠物："+(user01.getPet() == tomcatPet));


        //5.获取组件
        String[] beanNamesForType =run.getBeanNamesForType(User.class);
        System.out.println("---------------------");
        for (String s:beanNamesForType) {
            System.out.println(s);
        }

        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("haha："+haha);//true
        System.out.println("hehe："+hehe);//tru


    }

}
