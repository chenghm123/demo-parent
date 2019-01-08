package com.accelerator.demo.standalone.javase.asserts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyWorkAssertDemo {

    protected static final Logger logger = LoggerFactory.getLogger(KeyWorkAssertDemo.class);

    /**
     * assert关键字用法简单，但是使用assert往往会让你陷入越来越深的陷阱中。应避免使用。
     * 经过研究，总结了以下原因：
     * 1、assert关键字需要在运行时候显式开启才能生效，否则你的断言就没有任何意义。
     * 而现在主流的Java IDE工具默认都没有开启-ea断言检查功能。(java -ea KeyWorkAssertDemo)
     * 这就意味着你如果使用IDE工具编码，调试运行时候会有一定的麻烦。
     * 并且，对于Java Web应用，程序代码都是部署在容器里面，你没法直接去控制程序的运行，如果一定要开启-ea的开关，
     * 则需要更改Web容器的运行配置参数。这对程序的移植和部署都带来很大的不便。
     * 2、用assert代替if是陷阱之二。
     * assert的判断和if语句差不多，但两者的作用有着本质的区别：
     * assert关键字本意上是为测试调试程序时使用的，
     * 但如果不小心用assert来控制了程序的业务流程，
     * 那在测试调试结束后去掉assert关键字就意味着修改了程序的正常的逻辑。
     * 3、assert断言失败将面临程序的退出。
     * 这在一个生产环境下的应用是绝不能容忍的。
     * 一般都是通过异常处理来解决程序中潜在的错误。
     * 但是使用断言就很危险，一旦失败系统就挂了。
     */
    public static void main(String[] args) {
        logger.info("开始使用assert关键字");

        //断言1结果为true，则继续往下执行
        assert true;
        logger.info("断言1没有问题，Go！");

        logger.info("-----------------");

        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        logger.info("断言2没有问题，Go！");

        logger.info("结束使用assert关键字");
    }
    /**
     * assert既然是为了调试测试程序用，不在正式生产环境下用，
     * 那应该考虑更好的测试JUint来代替其做用，
     * JUint相对assert关键的所提供的功能是有过之而无不及。
     * 当然完全可以通过IDE debug来进行调试测试。
     * 在此看来，assert的前途一片昏暗。
     *  因此，应当避免在Java中使用assert关键字，除非哪一天Java默认支持开启-ea的开关，
     *  这时候可以考虑。对比一下，assert能给你带来多少好处，多少麻烦，这是我们选择是否使用的的原则。
     */
}
