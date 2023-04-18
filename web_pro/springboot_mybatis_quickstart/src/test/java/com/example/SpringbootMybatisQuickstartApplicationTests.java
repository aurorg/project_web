package com.example;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest //springboot整合单元测试的注解
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUser(){
        List<User> userList =userMapper.list();
        userList.stream().forEach(user->{
            System.out.println(user);
        });
    }

    @Test
    public void testJdbc() throws Exception{
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2获取连接对象
        String url="jdbc:mysql://localhost:3306";
        String username="root";
        String password="szl0905";
        Connection connection= DriverManager.getConnection(url,username,password);

        //获取执行SQL的对象Statement，执行SQL，返回结果
        String sql="select * from mybatis.user";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);

        //封装结果数据
        List<User> userList = new ArrayList<>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            short age=resultSet.getShort("age");
            short gender=resultSet.getShort("gender");
            String phone=resultSet.getString("phone");

            User user=new User(id,name,age,gender,phone);
            userList.add(user);
        }


        //5.释放资源
        statement.close();
        connection.close();


        userList.stream().forEach(user->{
            System.out.println(user);
        });
    }

}
