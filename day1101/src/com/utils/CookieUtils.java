package com.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (name.equals(c)) {
					return c;
				}
			}
		}
		return null;
	}
}
