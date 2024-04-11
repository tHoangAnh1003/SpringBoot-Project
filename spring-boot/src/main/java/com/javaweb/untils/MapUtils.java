package com.javaweb.untils;

import java.util.Map;

public class MapUtils {
	public static<T> T getObject(Map<String, Object> ob, String key, Class<T> tClass) {
		Object obj = ob.getOrDefault(key, null);
		if (obj != null) {
			if (tClass.getTypeName().equals("java.lang.Long")) {
				obj = (obj != "") ? Long.valueOf(obj.toString()) : null;  
			}
			else if (tClass.getTypeName().equals("java.lang.Integer")) {
				obj = (obj != "") ? Integer.valueOf(obj.toString()) : null;  
			} 
			else if (tClass.getTypeName().equals("java.lang.String")) {
				obj = obj.toString();
			} 
		}
		return null;
	}
}
