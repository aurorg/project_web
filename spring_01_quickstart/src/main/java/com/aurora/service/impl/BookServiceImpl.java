package com.aurora.service.impl;

import com.aurora.dao.BookDao;
import com.aurora.dao.impl.BookDaoImpl;
import com.aurora.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    public void save(){
        System.out.println("book service save...");
        bookDao.save();
    }
}
