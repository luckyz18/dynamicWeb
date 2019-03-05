package com.domain;

public class Person {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void eat() {
		System.out.println("会吃");
	}
	public void eat(String name) {
		System.out.println(name+"会吃");
	}
	
	private void sleep() {
		System.out.println("会睡觉");
	}
	private String sleep(String name) {
		return name+"会睡觉";
	}
	public Person(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		System.out.println("有参");
	}
	public Person() {
		System.out.println("空参");

	}
	
	
	
}
