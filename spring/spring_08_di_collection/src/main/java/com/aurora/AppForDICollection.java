package com.aurora;

import com.aurora.dao.BookDao;
import com.aurora.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForDICollection {
    public static void main( String[] args ) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao)ctx.getBean("bookDao");
        bookDao.save();
    }
}

