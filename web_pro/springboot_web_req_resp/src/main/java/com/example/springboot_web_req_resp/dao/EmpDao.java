package com.example.springboot_web_req_resp.dao;

import com.example.springboot_web_req_resp.pojo.Emp;

import java.util.List;

public interface EmpDao {

    //获取员工数据
    public List<Emp> listEmp();
}
