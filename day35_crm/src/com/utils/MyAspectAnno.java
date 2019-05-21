package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;



/**
 * 使用注解的方式  切面类
 * @author 祝丽华
 *
 */
@Aspect
public class MyAspectAnno {
	/**
	 * 通知类型：@Before(切入点表达式)
	 */
//	@Before(value = "execution(public void demo.CustomerDaoImpl.save())")
//	public void log() {
//		System.out.println("日志 注解test...");
//	}
	
	@Before(value = "MyAspectAnno.fn()")
	public void log() {
		System.out.println("日志 注解test...");
	}
	
	@Around(value = "MyAspectAnno.fn()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("环绕通知1");
		joinPoint.proceed();
		System.out.println("环绕通知2");
	}
	
	
	/*定义通用的切入点*/
	@Pointcut(value="execution(public void demo.CustomerDaoImpl.save())")
	public void fn() {}
}
