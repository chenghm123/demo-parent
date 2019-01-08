package com.accelerator.demo.standalone.javase.bio;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Accelerator on 2016/8/23.
 */
public class FileOutDemo {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("C:/Users/Accelerator/Desktop/a.txt")) {
            out.write("a".getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
