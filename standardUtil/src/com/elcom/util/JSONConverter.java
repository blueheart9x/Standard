package com.elcom.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JSONConverter {
	
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public static String toJSON(Object obj){
		return gson.toJson(obj);
	}
	
	public static <T> T toObject(String value,Class<T> actualObject) {
		try{
			return gson.fromJson(value, actualObject);
		}catch(JsonSyntaxException ex){
			return null;
		}
	}

	
	private static String getClassName(String input) {
        if (input == null) return "";
        int index = input.lastIndexOf(".");
        if (index != -1) {
            input = input.substring(index);
        }
        if (input.lastIndexOf(".") != -1) {
            input = input.replace(".", "");
        }
        return input;
    }

    public static < T > T toObject(Class < T > classInput, String content) {
        @SuppressWarnings("unchecked")
		Map < String, Object > r = gson.fromJson(content, Map.class);
        String className = getClassName(classInput.getName());
        String innerJson = gson.toJson(r.get(className));
        T _r = gson.fromJson(innerJson, classInput);
        return _r;
    }
}
