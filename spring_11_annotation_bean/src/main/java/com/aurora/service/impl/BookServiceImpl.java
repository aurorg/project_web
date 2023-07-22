package com.aurora.service.impl;

import com.aurora.dao.BookDao;
import com.aurora.dao.impl.BookDaoImpl;
import com.aurora.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class BookServiceImpl implements BookService {
    //5.删除业务层中使用new的方式创建dao对象
    private BookDao bookDao;

    public void save() {
        System.out.println("book service save...");
        bookDao.save();
    }

}