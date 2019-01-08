package com.accelerator.demo.standalone.javase.constrseq;

public class FatherObject {

    static {
        System.out.println("执行父类的静态代码块。");
    }

    public FatherObject() {
        System.out.println("执行父类的不带参数的构造方法。");
    }

    public FatherObject(int num) {
        System.out.println("执行父类的带参数的构造方法。");
    }

    public FatherObject(String str) {
        System.out.println("执行父类的带参数的构造方法。");
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i + j);
        System.out.println("执行父类的构造代码块。" + sum);
    }

    {
        int i = 1;
        int j = 2;
        int sum = (i + j);
        System.out.println("执行父类的构造代码块。" + sum);
    }

    {
        int m = 3;
        int n = 4;
        int sum = (m + n);
        System.out.println("执行父类的构造代码块。" + sum);
    }

}
