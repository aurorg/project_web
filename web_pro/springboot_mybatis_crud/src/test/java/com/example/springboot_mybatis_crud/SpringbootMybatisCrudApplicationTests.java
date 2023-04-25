package com.example.springboot_mybatis_crud;

import com.example.springboot_mybatis_crud.mapper.EmpMapper;
import com.example.springboot_mybatis_crud.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        empMapper.delete(17);
    }

    @Test
    public void testInsert(){
        //构造员工对象
        Emp emp=new Emp();
        emp.setUsername("Tom3");
        emp.setName("汤姆");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        //执行新增员工信息操作
        empMapper.insert(emp);
        System.out.println(emp.getId());

    }

    @Test
    public void testUpdate(){
        //构造员工对象
        Emp emp=new Emp();
        emp.setId(18);
        emp.setUsername("Tom111");
        emp.setName("汤姆111");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);


        //执行更新员工操作
        empMapper.update(emp);

    }

    @Test
    public void testGetById(){
        Emp emp=empMapper.getById(10);
        System.out.println(emp);
    }


    @Test
    public void testList(){
//        List<Emp> emplist=empMapper.list("张", Short.parseShort("1"),LocalDate.of(2010,1,1),LocalDate.of(2020,1,1));
        List<Emp> emplist=empMapper.list("张",null,null,null);
        System.out.println(emplist);
    }



//更新有问题
    @Test
    public void testUpdate2(){
        //构造员工对象
        Emp emp=new Emp();
        emp.setId(14);
        emp.setUsername("Tom14");
        emp.setName("汤姆14");
        emp.setGender((short)2);
        emp.setUpdateTime(LocalDateTime.now());


        //执行更新员工操作
        empMapper.update(emp);

    }

    //批量删除员工
    @Test
    public void testDeleteByIds(){
        List<Integer> ids= Arrays.asList(13,14,15);
        empMapper.deleteByIds(ids);
    }
}
