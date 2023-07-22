package com.aurora;

import com.aurora.dao.BookDao;
import com.aurora.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {
    public static void main(String[] args){
        //3.获取ioc容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        //4.获取bean
//        BookDao bookDao=(BookDao)ctx.getBean("bookDao"); //转类型’
//        bookDao.save();

        BookService bookService= (BookService) ctx.getBean("bookService");
        bookService.save();
    }
}
