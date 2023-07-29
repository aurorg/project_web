package com.example.mybatisplus_01_quickstart;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus_01_quickstart.dao.UserDao;
import com.example.mybatisplus_01_quickstart.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
//        //方式一：按条件查询
//        QueryWrapper qw = new QueryWrapper();
//        qw.lt("age",18); //查询年龄小于十八岁的
//        List<User> userList=userDao.selectList(qw);
//        System.out.println(userList);

//        //方式二：lambda格式按条件查询
//        QueryWrapper<User> qw = new QueryWrapper<User>();
//        qw.lambda().lt(User::getAge,10);//查询年龄小于十八岁的
//        List<User> userList=userDao.selectList(qw);
//        System.out.println(userList);


        // 方式三：lambda格式按条件查询
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();

        //3-10之间
//        lqw.lt(User::getAge,10).gt(User::getAge,3);

        //小于3 大于10
        lqw.lt(User::getAge,10).or().gt(User::getAge,30);

        List<User> userList=userDao.selectList(lqw);
        System.out.println(userList);

    }


}
