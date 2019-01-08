package com.accelerator.demo.standalone.javase.constrseq;

public class SonObject extends FatherObject {

    static {
        System.out.println("执行子类的静态代码块。");
    }

    SonObject() {
        System.out.println("执行子类的不带参数的构造方法。");
    }

    SonObject(int num) {
        super(7);
        System.out.println("执行子类的带参数的构造方法。");
    }

    SonObject(String str) {
        super(7);
        System.out.println("执行子类的带参数的构造方法。");
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i + j);
        System.out.println("执行子类的构造代码块。" + sum);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i + j);
        System.out.println("执行子类的构造代码块。" + sum);
    }

    {
        int m = 3;
        int n = 4;
        int sum = (m + n);
        System.out.println("执行子类的构造代码块。" + sum);
    }

}
