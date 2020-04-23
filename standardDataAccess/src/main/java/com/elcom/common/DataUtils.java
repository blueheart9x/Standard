package com.elcom.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.elcom.data.DataException;
import com.elcom.model.object.ColumnIndex;

public final class DataUtils {

	private DataUtils() {
	}
	
	private static Method getMethod(String fName, @SuppressWarnings("rawtypes") Class entity) {
		Method[] methods = entity.getDeclaredMethods();
		for(Method m : methods) {
			if("set".equals(m.getName().substring(0, 3))) {
				if(fName.toUpperCase().equals(m.getName().substring(3, m.getName().length()).toUpperCase()))
					return m;
			}
		}
		return null;
	}

	public static Object bindingData(Object instance, Object[] objs) {
		if(instance == null) {
			throw new DataException("bindingData: No class definition found");
		}

		if(objs == null) {
			throw new DataException("bindingData: No input data found");
		}
		@SuppressWarnings("rawtypes")
		Class entity = instance.getClass();
		int j=0;
		try {
			for(Object object : objs) {
				if(object != null) {
					for(Field field : entity.getDeclaredFields()) {
						if(field.isAnnotationPresent(ColumnIndex.class)) {
							ColumnIndex ci = field.getAnnotation(ColumnIndex.class);
							if(ci.index() == j) {
							Method method = getMethod(field.getName(), entity);
							if(method != null) {
									if(object.getClass().equals(BigDecimal.class)) {
										if(field.getType().equals(Integer.class)) {
											method.invoke(instance, ((BigDecimal)object).intValue());
										} else if(field.getType().equals(Long.class)) {
											method.invoke(instance, ((BigDecimal)object).longValue());
										} else {
											method.invoke(instance, object);
										}
									} else {
										method.invoke(instance, object);
									}
								}
							}
						}
					}
				}
				j++;
			}
		} catch (Exception ex) {
			throw new DataException("bindingData: Unknown exception="+ex.getMessage());
		}
		return instance;
	}
	
	public static Object getMethodResult(Object instance, String methodName) {
		if(instance == null) {
			throw new DataException("getMethodResult: No class definition found");
		}

		if( methodName == null || "".equals(methodName) ) {
			throw new DataException("getMethodResult: No method found");
		}

		@SuppressWarnings("rawtypes")
		Class entity = instance.getClass();
		try {
			Method[] methods = entity.getDeclaredMethods();
			Method method = null;
			for(Method m : methods) {
				if(methodName.equals(m.getName())) {
					method = m;
					break;
				}
			}
			if(method != null) {
				return method.invoke(instance);
			} else {
				throw new DataException("Method " + methodName + " cannot be found in class "+ entity.getName() +"!");
			}
		} catch (Exception ex) {
			throw new DataException("getMethodResult: Exception="+ex.getMessage());
		}
	}

	public static Object findObject(String clsName, Object... objects) {
		if(clsName == null || "".equals(clsName)) {
			throw new RuntimeException("getParameterObject: No class found");
		}
		// Go through to find object instance
		for(Object instance : objects) {
			if(instance == null) {
				throw new RuntimeException("getParameterObject: Object instance is null!");
			}
			@SuppressWarnings("rawtypes")
			Class cls = instance.getClass();
			if(clsName.equals(cls.getName())) {
				return instance;
			}
		}
		System.out.println("Cannot find object instance of class="+clsName);
		return null;
	}
}
