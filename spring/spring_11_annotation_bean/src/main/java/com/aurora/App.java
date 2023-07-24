package com.aurora;

import com.aurora.dao.BookDao;
import com.aurora.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml.bak");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        System.out.println(bookDao);
        BookService bookService=ctx.getBean(BookService.class);
        System.out.println(bookService);
    }
}