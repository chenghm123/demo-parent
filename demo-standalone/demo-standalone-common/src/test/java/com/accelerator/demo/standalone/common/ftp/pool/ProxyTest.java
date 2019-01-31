package com.accelerator.demo.standalone.common.ftp.pool;

import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {

    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FTPClient.class);
        enhancer.setCallback(new TargetProxy());
        enhancer.setInterceptDuringConstruction(false);
        FTPClient ftpClient1 = (FTPClient) enhancer.create();
        FTPClient ftpClient2 = (FTPClient) enhancer.create();
        FTPClient ftpClient3 = (FTPClient) enhancer.create();
        System.out.println(ftpClient1.equals(ftpClient2));
        System.out.println(ftpClient1.equals(ftpClient3));
        System.out.println(ftpClient2.equals(ftpClient3));
    }

}

@AllArgsConstructor
class TargetProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        if ("logout".equals(method.getName())) {
            return true;
        } else if ("disconnect".equals(method.getName())) {
            return null;
        }
        return proxy.invokeSuper(obj, args);
    }

}
