package com.accelerator.demo.standalone.dubbo.callback;

import com.accelerator.demo.standalone.dubbo.callback.api.CallbackListener;
import com.accelerator.demo.standalone.dubbo.callback.api.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallbackConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackConsumer.class);

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:callback/callback-consumer.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");
        callbackService.addListener("foo.bar", new CallbackListener() {
            public void changed(String msg) {
                LOGGER.info("callback1:{}", msg);
            }
        });
    }

}
