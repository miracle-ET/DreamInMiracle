﻿package com.cy.pj.sys.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class mapWithEntity {
	public static <T> Map<String, Object> beanToMap(T bean) {

        Map<String, Object> map = new LinkedHashMap<>();  //按插入的顺序进行有序存储
        Class<?> clazz = bean.getClass();

        for (Field field : clazz.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        if(map.get("serialVersionUID")!=null) {
        	map.remove("serialVersionUID");
        }
        return map;
    }


    /**
     * 将Map对象通过反射机制转换成Bean对象
     *
     * @param map   存放数据的map对象
     * @param clazz 待转换的class
     * @return 转换后的Bean对象
     * @throws Exception 异常
     */
    public static Object mapToBean(Map<String, Object> map, Class<?> clazz) {
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();       //属性名
                Object value = entry.getValue();
                String setMethodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);
                if (field == null)
                    continue;
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try {
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    /**
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
     *
     * @param clazz     指定的class
     * @param fieldName 字段名称
     * @return Field对象
     */
    public static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value          Object对象值
     * @param fieldTypeClass 属性的类型
     * @return 转换后的值
     */
    public static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;
        if (value == null) {
            retVal = value;
        } else {
            if (Long.class.getName().equals(fieldTypeClass.getName())
                    || long.class.getName().equals(fieldTypeClass.getName())) {
                retVal = Long.parseLong(value.toString());
            } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                    || int.class.getName().equals(fieldTypeClass.getName())) {
                retVal = Integer.parseInt(value.toString());
            } else if (Float.class.getName().equals(fieldTypeClass.getName())
                    || float.class.getName().equals(fieldTypeClass.getName())) {
                retVal = Float.parseFloat(value.toString());
            } else if (Double.class.getName().equals(fieldTypeClass.getName())
                    || double.class.getName().equals(fieldTypeClass.getName())) {
                retVal = Double.parseDouble(value.toString());
            } else {
                retVal = value;
            }
        }

        return retVal;
    }
    public static Object updateBean(Object src,Object desc) throws Exception {
        Class<?> srcClass = src.getClass();
        Class<?> descClass = desc.getClass();
        if(!descClass.equals(srcClass)){
            throw new Exception("源对象与目标对象类型不一致！");
        }
 
        Field[] ClassFields = srcClass.getDeclaredFields();

 
        for (int i = 0; i < ClassFields.length; i++) {
            ClassFields[i].setAccessible(true);
            Object o = ClassFields[i].get(src);
            if(o==null) {
            	ClassFields[i].set(src, ClassFields[i].get(desc));
            }
        }
 
        return src;
    }
}
