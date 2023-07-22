package com.aurora.service.impl;

import com.aurora.dao.BookDao;
import com.aurora.dao.impl.BookDaoImpl;
import com.aurora.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
