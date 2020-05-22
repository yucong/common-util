package com.java.util.test;

import java.util.HashMap;
import java.util.Map;

import com.java.util.json.FastJsonUtil;

public class TestJsonUtil {

	public static void main(String[] args) {
		jsonToMap();
	}
	
	/**
	 * 将对象转换成json字符串
	 */
	public static void mapToJson() {
		Map<String,Object> map = new HashMap<>();
		map.put("name", "yucong");
		map.put("age", 28);
		map.put("sex", "保密");
		
		System.out.println(FastJsonUtil.toJson(map));
	}
	
	/**
	 * 将json字符串转换成对象
	 */
	public static void jsonToMap() {
		String json = "{\"sex\":\"保密\",\"name\":\"yucong\",\"age\":28}";
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String, Object>) FastJsonUtil.parseJsonObject(json, Map.class);
		System.out.println(map);
	}
	
}
