package com.aurora.dao.impl;

import com.aurora.dao.ResourcesDao;
import org.springframework.stereotype.Repository;

@Repository
public class ResourcesDaoImpl implements ResourcesDao {
    public boolean readResources(String url, String password) {
         System.out.println(password.length());
        //模拟校验
        return password.equals("root");
    }
}
