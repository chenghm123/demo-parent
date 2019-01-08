/*
 * Project:  signbar
 * Module:   signbar
 * File:     AuthcFilter.java
 * Modifier: ChengHaiMing
 * Modified: 2015-06-25 22:45
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

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class AuthcFilter extends FormAuthenticationFilter {

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String contextPath = request.getServletContext().getContextPath();
        String successUrl = contextPath + getSuccessUrl();
        WebUtils.redirectToSavedRequest(request, response, successUrl);
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String contextPath = request.getServletContext().getContextPath();
        String loginUrl = contextPath + getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
