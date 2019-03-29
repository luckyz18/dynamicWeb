package com.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class myconvert implements Converter {

	@Override
	//自定义转换器  要在使用之前注册
	public Object convert(Class clazz, Object value) {   //clazz 是要转换成的类型  value是前台传过来的类型   
		//字符串转成Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse((String) value);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
