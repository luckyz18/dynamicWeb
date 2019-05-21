package com.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UserTest {
	/*集合注入*/
	/*数组 或者 list*/
	private String[] arr;
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	
	/*set*/
	private Set<CarTest> cars;
	public void setCar(Set<CarTest> cars) {
		this.cars = cars;
	}
	public void setCars(Set<CarTest> cars) {
		this.cars = cars;
	}
	
	private Map<String, CarTest> map;
	public void setMap(Map<String, CarTest> map) {
		this.map = map;
	}
	
	

	private Properties pro;
	public void setPro(Properties pro) {
		this.pro = pro;
	}
	@Override
	public String toString() {
		return "UserTest [arr=" + Arrays.toString(arr) + ", cars=" + cars + ", map=" + map + ", pro=" + pro + "]";
	}

	

	
	


	

	
}
