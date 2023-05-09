package com.example.springboot_web_req_resp.service.impl;

import com.example.springboot_web_req_resp.dao.EmpDao;
import com.example.springboot_web_req_resp.dao.impl.EmpDaoA;
import com.example.springboot_web_req_resp.pojo.Emp;
import com.example.springboot_web_req_resp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Primary
//如果同类型的bean存在多个；
/*
@Primary
@Autowired+@Qualifier("bean的名称“）
@Resource(name="bean的名称“）
 */
@Service
//@Component //将当前类交给ioc容器管理，称为ioc容器中的bean
public class EmpServiceA implements EmpService {

    @Autowired
    //运行时，ioc容器会提供该类型的bean对象并且赋值给该变量--依赖注入
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {

        //调用dao，获取数据
        List<Emp> empList =empDao.listEmp();
        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
