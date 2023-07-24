package com.aurora.dao.impl;

import com.aurora.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    public String findName(int id, String name) {
        System.out.println("id:"+id);
        return "itcast";
    }
}
