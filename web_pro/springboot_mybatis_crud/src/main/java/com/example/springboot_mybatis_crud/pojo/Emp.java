package com.example.springboot_mybatis_crud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id; //ID
    private String username; //用户名
    private String password; //密码
    private String name;  //姓名
    private short gender; //性别，1男，2女
    private String image;//图像url
    private short job; //职位，1班主任，2讲师，3学生主管，4教研主管，5咨询师
    private LocalDate entrydate; //入职日期
    private Integer deptId; //部门id
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
