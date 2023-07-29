package com.example.mybatisplus_01_quickstart;

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
    void testSave(){
        User user= new User();
        user.setName("aurora");
        user.setPassword("123");
        user.setAge(18);
        user.setTel("888888");
        userDao.insert(user);
    }

    @Test
    void testDelete(){
        userDao.deleteById(1685102604974755842L);

    }

    @Test
    void testUpdate(){
        User user= new User();
        user.setId(1L);
        user.setName("hahaha");
        userDao.updateById(user);
    }

    @Test
    void testGetById(){
        User user=userDao.selectById(2L);
        System.out.println(user);
    }

    @Test
    void testGetAll() {
        List<User> userList=userDao.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testGetByPage(){
        //配置分页拦截器
        IPage page = new Page(1,2);
        userDao.selectPage(page,null);
        System.out.println("当前页码值" + page.getCurrent());
        System.out.println("每页显示数" + page.getSize());
        System.out.println("一共多少页" + page.getPages());
        System.out.println("一共多少条数据" + page.getTotal());
        System.out.println("数据" + page.getRecords());

    }

}
