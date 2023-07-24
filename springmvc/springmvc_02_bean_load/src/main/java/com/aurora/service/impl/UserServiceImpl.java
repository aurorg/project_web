package com.aurora.service.impl;

import com.aurora.domain.User;
import com.aurora.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public void save(User user) {
        System.out.println("user service ...");
    }
}
