package com.example.springboot_mybatis_crud;

import com.example.springboot_mybatis_crud.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;



    @Test
    public void testDelete(){
        empMapper.delete(17);
    }


}
