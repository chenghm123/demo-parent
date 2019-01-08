package com.accelerator.demo.standalone.springsession.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping
    public void index(HttpSession session) {
        logger.info("IndexController.index(HttpSession session) =============开始==============");
        List<Map<String, String>> sessionValue = (List<Map<String, String>>) session.getAttribute("attrs");
        if (sessionValue == null) {
            List<Map<String, String>> attrs = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                Map<String, String> attr = Maps.newHashMap();
                attr.put("username", "chenghm123");
                attr.put("nickname", "程小明");
                attrs.add(attr);
            }
            session.setAttribute("attrs", attrs);
        }
        logger.info("IndexController.index(HttpSession session) =============结束==============");
    }

}
