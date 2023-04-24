package com.example.springboot_mybatis_crud.mapper;


import com.example.springboot_mybatis_crud.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

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


    //根据id查询员工
    @Select("select * from mybatis.emp where id= #{id}")
     public Emp getById(Integer id);

//    //方案一：给字段起别名，让别名与实体类属性一致
//      @Select("select id, username, password, name, gender, image, job, entrydate," +
//              " dept_id deptId, create_time createTime, update_time  updateTime from mybatis.emp where id= #{id}")
//      public Emp getById(Integer id);


//    //方案二：通过@Results,@Result注解手动映射封装
//    @Results(  {
//            @Result(column = "dept_id", property = "deptId"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select * from mybatis.emp where id= #{id}")
//     public Emp getById(Integer id);


    //方案三：开启mybatis的驼峰命名自动映射开发    ---s_column  -->sColumn


//    //条件查询员工
//    @Select("select * from mybatis.emp where name like '%${name}%' and gender = #{gender} and " +
//            "entrydate between #{begin} and #{end} order by update_time desc")
//    public List<Emp> list(String name, short gender, LocalDate begin,LocalDate end);


        //条件查询员工
//    @Select("select * from mybatis.emp where name like concat('%' ,#{name},'%') and gender = #{gender} and " +
//            "entrydate between #{begin} and #{end} order by update_time desc")
//    public List<Emp> list(String name, short gender, LocalDate begin,LocalDate end);

    public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    //动态更新数据
    public void update2(Emp emp);

}
