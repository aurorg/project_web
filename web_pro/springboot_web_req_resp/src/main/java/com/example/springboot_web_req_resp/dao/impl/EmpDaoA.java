package com.example.springboot_web_req_resp.dao.impl;

import com.example.springboot_web_req_resp.dao.EmpDao;
import com.example.springboot_web_req_resp.pojo.Emp;
import com.example.springboot_web_req_resp.utils.XmlParserUtils;

import java.util.List;

public class EmpDaoA implements EmpDao {

    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
