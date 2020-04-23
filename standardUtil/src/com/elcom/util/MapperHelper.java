package com.elcom.util;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MapperHelper {
	public <T> T transformObject(Object item, Class<T> outputOBJ) {
		Field[] fieldsIn = item.getClass().getDeclaredFields();
		HashMap< String, Object> hashIn = new HashMap<>();
		
		
		
		
		Field[] fieldsOut = outputOBJ.getClass().getDeclaredFields();
		HashMap< String, Object> hashOut = new HashMap<>();
		
		
		return null;

	}
}
