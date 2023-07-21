package com.aurora;

import com.aurora.dao.OrderDao;
import com.aurora.factory.OrderDaoFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceOrder {
    //通过静态工厂创建对象
    public static void main(String[] args ){
//        OrderDao orderDao= OrderDaoFactory.getOrderDao();
//        orderDao.save();

        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        OrderDao orderDao=(OrderDao) ctx.getBean("orderDao");
        orderDao.save();
    }
}
