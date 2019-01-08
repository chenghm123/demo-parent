package com.accelerator.demo.standalone.cache.service;

import com.accelerator.demo.standalone.cache.model.User;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, User> userMap = Maps.newConcurrentMap();

    @CachePut(value = "defaultCache", key = "#user.username")
    public User setUser(User user) {
        userMap.put(user.getUsername(), user);
        return user;
    }

    @Cacheable(value = "defaultCache", key = "#username")
    public User getUser(String username) {
        logger.info("获取用户【{}】未经过缓存", username);
        return userMap.get(username);
    }

}
