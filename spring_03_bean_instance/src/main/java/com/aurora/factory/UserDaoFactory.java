package com.aurora.factory;

import com.aurora.dao.UserDao;
import com.aurora.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
