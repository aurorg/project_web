package com.example.springboot_web_req_resp.service;

import com.example.springboot_web_req_resp.pojo.Emp;

import java.util.List;

public interface EmpService {
    //获取员工列表
    public List<Emp> listEmp();
}
