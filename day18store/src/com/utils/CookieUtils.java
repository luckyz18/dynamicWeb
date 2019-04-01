package com.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie i : cookies) {
				if (name.equals(i.getName())) {   //出错 没有 getname
					return i;
				}
			}
		}
		return null;
	}
}
