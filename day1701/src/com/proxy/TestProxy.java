package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
	
	public static void main(String[] args) {
		final QQCar qqCar= new QQCar();
		
		//创建代理对象  虚拟的
		Car qqProxy = (Car) Proxy.newProxyInstance(QQCar.class.getClassLoader(), new Class[] {Car.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//对所有方法进行加强
				/*System.out.println("加满油");
				Object obj = method.invoke(qqCar, args);
				System.out.println("跑5m");	*/
				
				//只对run 加强 对stop不加强
				if ("run".equals(method.getName())) {
					System.out.println("加满油");
					Object obj = method.invoke(qqCar, args);
					System.out.println("跑5m");
					return obj;
				}else {
					return method.invoke(qqCar, args);
				}
			}
		});
		
		qqProxy.run();
		qqProxy.stop();
		
		
	}
}

