package com.utils;

import java.util.UUID;

public class UploadUtils {
	public static String getUUIDName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String lastName = fileName.substring(index, fileName.length());
		String uuidName = UUID.randomUUID().toString().replace("-", "");
		return uuidName+lastName;
	}
	
	public static void main(String[] args) {
		String fileName = "gdgdg.jpg";
		System.out.println(getUUIDName(fileName));
	}
}	
