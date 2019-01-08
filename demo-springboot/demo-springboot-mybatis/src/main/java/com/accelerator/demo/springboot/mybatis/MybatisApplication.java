package com.accelerator.demo.springboot.mybatis;

import com.accelerator.demo.springboot.mybatis.mapper.UserMapper;
import com.accelerator.demo.springboot.mybatis.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication // @MapperScan(basePackages = "com.accelerator.demo.springboot.mybatis.mapper") 此注解可以让通用Mapper不打@Mapper
public class MybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MybatisApplication.class, args);
        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        User user = new User();
        user.setUsername("chenghm123");
        user.setPassword("chegnhm123");
        userMapper.insert(user);
    }

}
