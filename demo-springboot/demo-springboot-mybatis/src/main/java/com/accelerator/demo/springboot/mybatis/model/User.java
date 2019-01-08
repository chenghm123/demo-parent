package com.accelerator.demo.springboot.mybatis.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data @Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    // 没有这个@Id注解 根据主键查询无效
    @Id /** 主键ID */
    private Integer id;

}