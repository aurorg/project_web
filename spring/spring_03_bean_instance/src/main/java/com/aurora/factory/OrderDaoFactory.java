package com.aurora.factory;

import com.aurora.dao.OrderDao;
import com.aurora.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        System.out.println("factory setup....");
        return new OrderDaoImpl();
    }
}

