package com.accelerator.demo.standalone.validator.model;

import com.accelerator.demo.standalone.validator.Group;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @NotNull(message = "用户名不能为空", groups = Group.class)
    private String username;

    @NotNull(message = "密码为空")
    private String password;

}
