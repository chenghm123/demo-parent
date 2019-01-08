package com.accelerator.demo.standalone.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopDemoAspect {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut(value = "execution(* AopDemoService.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        logger.info("Aspect Before 被调用！");
    }

    @After(value = "pointcut()")
    public void after(JoinPoint point) {
        logger.info("Aspect After 被调用！");
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("Aspect Around 调用开始！");
        Object result = point.proceed();
        logger.info("Around调用返回值：{}", result);
        logger.info("Aspect Around 调用结束！");
        return result;
    }

    @AfterReturning(value = "pointcut()", returning = "retVal")
    public void afterReturning(JoinPoint point, Object retVal) {
        logger.info("AfterReturning 被调用！");
        // 如果Around方法返回值为空、那么这里的返回值也是空
        logger.info("AfterReturning调用返回值：{}", retVal);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) {
        logger.info("AfterThrowing调用异常信息：{}", e.getMessage());
    }

}
