package com.accelerator.demo.springboot.mybatis.mapper;

import com.accelerator.demo.springboot.mybatis.DemoMapper;
import com.accelerator.demo.springboot.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends DemoMapper<User> {}