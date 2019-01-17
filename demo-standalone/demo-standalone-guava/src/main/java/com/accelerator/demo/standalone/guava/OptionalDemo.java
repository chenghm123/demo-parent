package com.accelerator.demo.standalone.guava;


import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class OptionalDemo {

    public static void main(String[] args) {
        // 创建指定引用的Optional实例，若引用为null则快速失败
        Optional<Integer> optional = Optional.of(5);
        // 创建引用缺失的Optional实例
        optional = Optional.absent();
        // 创建指定引用的Optional实例，若引用为null则表示缺失
        optional = Optional.fromNullable(null);
        //如果Optional包含非null的引用（引用存在），返回true
        boolean flag = optional.isPresent();
        // 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        Integer ov = optional.get();
        // 返回Optional所包含的引用，若引用缺失，返回指定的值
        ov = optional.or(1);
        // 返回Optional所包含的引用，若引用缺失，返回null
        ov = optional.orNull();
        // 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
        Set<Integer> ovs = optional.asSet();
    }

}
