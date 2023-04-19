package com.example.springboot_mybatis_crud.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {

    //根据id删除数据
    @Delete("delete from mybatis.emp where id=#{id}")
    public void delete(Integer id);

}
