package com.aurora.dao.impl;

import com.aurora.dao.BookDao;

public class BookDaoImpl implements BookDao {

    //使用构造方法实例化对象（无参）
    public BookDaoImpl() {
        System.out.println("book dao constructor is running ....");
    }

    public void save() {
        System.out.println("book dao save ...");
    }

}