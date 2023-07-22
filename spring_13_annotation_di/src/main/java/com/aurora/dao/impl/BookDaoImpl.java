package com.aurora.dao.impl;

import com.aurora.dao.BookDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements BookDao {
    public void save(){
        System.out.println("book dao save");
    }
}
