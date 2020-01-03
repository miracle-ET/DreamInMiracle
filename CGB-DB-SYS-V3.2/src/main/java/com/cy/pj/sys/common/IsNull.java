package com.cy.pj.sys.common;

import java.lang.reflect.Field;

public class IsNull {
	public static Boolean isNull(Object obj) {
		if(obj==null) {
			return true;
		}
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(field.get(obj)==null) {
					return true;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
