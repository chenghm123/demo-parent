/*
 * Project:  signbar
 * Module:   signbar
 * File:     ShiroRealm.java
 * Modifier: ChengHaiMing
 * Modified: 2015-06-25 23:33
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

import com.google.common.collect.ImmutableList;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    private List<String> users = ImmutableList.of("admin", "user", "cheng", "zhang", "li");

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();
        String username = (String) getAvailablePrincipal(principals);
        if ("admin".equals(username)) {
            authorInfo.addRole("admin");
        } else {
            authorInfo.addRole("user");
        }
        return authorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        if (users.contains(username)) {
            return new SimpleAuthenticationInfo(username, "123", getName());
        }
        return null;
    }


}
