package demo;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类
 * @author 祝丽华
 *
 */
public class MyAspectXml {
	public void log() {
		System.out.println("打印日志切面test");
	}
	
	//环绕通知 默认的目标对象方法是不执行的 需要手动执行
	public void around(ProceedingJoinPoint joinPoint) {
		System.out.println("环绕通知1");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("环绕通知2");
	}
}
