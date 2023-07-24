package com.aurora;

import com.aurora.dao.BookDao;
import com.aurora.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForScope {
    public static void main(String[] args){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookDao bookDao1=(BookDao)ctx.getBean("bookDao");
        BookDao bookDao2=(BookDao)ctx.getBean("bookDao"); //转类型
        System.out.println(bookDao1);
        System.out.println(bookDao2);

    }
}
