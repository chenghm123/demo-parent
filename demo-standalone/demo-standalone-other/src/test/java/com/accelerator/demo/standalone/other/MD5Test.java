package com.accelerator.demo.standalone.other;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Test {

    public static void main(String[] args) {
        String str = "73.25";

        for (int i = 0; i < 100000000; i++) {
            str = DigestUtils.md2Hex(str);
        }

        System.out.println(str);
    }

}
