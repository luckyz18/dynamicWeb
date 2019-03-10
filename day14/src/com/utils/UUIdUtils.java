package com.utils;

import java.util.Date;
import java.util.UUID;

public class UUIdUtils {
	/**
	 * 生成随机ID
	 * @param args
	 */
	public  static String getId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();	
	}
	
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getCode() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
