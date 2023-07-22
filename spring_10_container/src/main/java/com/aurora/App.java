package com.aurora;

import com.aurora.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        //1.加载类路径下的配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从文件系统下加载配置文件
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("/home/shizhanli/project_web/spring_10_container/src/main/resources/applicationContext.xml");
//        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
//        BookDao bookDao = ctx.getBean("bookDao",BookDao.class);
//        BookDao bookDao = ctx.getBean(BookDao.class);
//        bookDao.save();
    }
}