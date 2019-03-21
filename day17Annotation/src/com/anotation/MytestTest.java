package com.anotation;

public class MytestTest {
	
	@MyTest
	public void f1(){
		System.out.println("f1执行");
	}
	
	public void f2(){
		System.out.println("f2执行");
	}
	@MyTest
	public void f3(){
		System.out.println("f3执行");
	}
}
