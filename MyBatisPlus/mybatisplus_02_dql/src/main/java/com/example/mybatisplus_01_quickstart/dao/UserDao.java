package com.example.mybatisplus_01_quickstart.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus_01_quickstart.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
