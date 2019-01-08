package com.accelerator.demo.standalone.common.random.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Random RANDOM = new Random();

    @Test
    public void test() {
        while (true) {
            String str = Integer.toString(RANDOM.nextInt(61));
            logger.info(str);
        }
    }

}
