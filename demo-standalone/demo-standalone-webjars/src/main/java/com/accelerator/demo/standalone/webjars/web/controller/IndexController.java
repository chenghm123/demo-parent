package com.accelerator.demo.standalone.webjars.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
