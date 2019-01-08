package com.accelerator.demo.standalone.dubbo.generic.impl;

import com.accelerator.demo.standalone.dubbo.generic.api.IUserService;

public class UserServiceImpl implements IUserService {

    public User get(Params params) {
        return new User(1, "charles");
    }

}
