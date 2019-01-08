/*
 * Project:  signbar
 * Module:   signbar
 * File:     UserFilter.java
 * Modifier: ChengHaiMing
 * Modified: 2015-06-26 01:26
 *
 * Copyright (c) 2015 Wuan Ltd. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package com.accelerator.demo.standalone.shiro.plugin;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class UsersFilter extends org.apache.shiro.web.filter.authc.UserFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String contextPath = request.getServletContext().getContextPath();
        String loginUrl = contextPath + getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
