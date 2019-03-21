package com.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JDBCInfo {
	String driverClass() default "com.mysql.jdbc.Driver";
	String url() default "jdbc:mysql://localhost:3306/day16";
	String user();
	String password();
	
}
