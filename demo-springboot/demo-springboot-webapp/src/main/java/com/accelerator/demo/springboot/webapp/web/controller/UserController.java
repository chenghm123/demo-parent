package com.accelerator.demo.springboot.webapp.web.controller;

import com.accelerator.demo.springboot.webapp.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("spring boot");
        return user;
    }

}
