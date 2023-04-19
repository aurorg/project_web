package com.example.springboot_mybatis_crud.mapper;


import com.example.springboot_mybatis_crud.pojo.Emp;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    //根据id删除数据
    @Delete("delete from mybatis.emp where id=#{id}")
    public void delete(Integer id);

    //新增员工
    @Options(useGeneratedKeys = true,keyProperty = "id")  //获取返回的主键
    @Insert("insert into mybatis.emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "            values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);


    //更新数据
    @Update("update mybatis.emp set username=#{username},name=#{name},gender=#{gender},image=#{image},job=#{job},entrydate=#{entrydate},dept_id=#{deptId},update_time=#{updateTime}  where id=#{id};")
    public void update(Emp emp);
}
