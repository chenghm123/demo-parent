package com.accelerator.demo.standalone.other;

import org.apache.commons.codec.digest.DigestUtils;

public class MDXTest {

    public static void main(String[] args) {
        String str = "39";

        for (int i = 0; i < 100000000; i++) {
            str = DigestUtils.md2Hex(str);
        }

        System.out.println(str);
    }

}
