package com.accelerator.demo.standalone.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("login")
    public Object login(String username, String password, String rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe("rememberMe".equals(rememberMe));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "User Not Found!";
        } catch (IncorrectCredentialsException e) {
            return "Password Wrong!";
        }
        return "SUCCESS";
    }

    @GetMapping("admin")
    public Object admin() {
        return SecurityUtils.getSubject()
                .getPrincipal();
    }

    @GetMapping("users")
    public Object user() {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        boolean remembered = subject.isRemembered();
        boolean runAs = subject.isRunAs();
        return SecurityUtils.getSubject()
                .getPrincipal();
    }

}
