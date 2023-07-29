package com.example.mybatisplus_01_quickstart;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus_01_quickstart.dao.UserDao;
import com.example.mybatisplus_01_quickstart.domain.User;
import com.example.mybatisplus_01_quickstart.domain.query.Userquery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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


        //模拟页面传递过来的查询数据
        Userquery uq = new Userquery();
        uq.setAge(10);
        uq.setAge2(30);




//        // 方式三：lambda格式按条件查询
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
//        //3-10之间
////        lqw.lt(User::getAge,10).gt(User::getAge,3);
//        //小于3 大于10
//        lqw.lt(User::getAge,uq.getAge2());
//        lqw.gt(User::getAge,uq.getAge());
//        List<User> userList=userDao.selectList(lqw);
//        System.out.println(userList);

//    //null判定
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
//        //先判断第一个条件是否为true，如果是true连接条件
//        lqw.lt(null != uq.getAge2(),User::getAge,uq.getAge2());
//        lqw.gt(null != uq.getAge(),User::getAge,uq.getAge());
//        List<User> userList=userDao.selectList(lqw);
//        System.out.println(userList);


        //查询投影
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
//        lqw.select(User::getId,User::getName,User::getAge);

//        QueryWrapper<User> lqw = new QueryWrapper<User>();
//        lqw.select("id","name","age","tel");
//        List<User> userList=userDao.selectList(lqw);
//        System.out.println(userList);

        //筛选 分组
//        QueryWrapper<User> lqw = new QueryWrapper<User>();
//        lqw.select("count(*) as count,tel");
//        lqw.groupBy("tel");
//        List<Map<String,Object>> userList=userDao.selectMaps(lqw);
//        System.out.println(userList);

        //条件查询
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //等同于=
        lqw.eq(User::getName,"Jerry").eq(User::getPassword,"jerry");
        User loginUser =userDao.selectOne(lqw);
        System.out.println(loginUser);

    }


}
