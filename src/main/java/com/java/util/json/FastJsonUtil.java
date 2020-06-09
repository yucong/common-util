package com.java.util.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * FastJson处理类
 * 
 * @author 喻聪
 * @date 2016-06-10
 * 
 */
public class FastJsonUtil {
	
	/**
	 * 
	 * 描述：将对象转化为json:Object(也支持List<?>,Map<?,?>,JSONObject)--->String
	 * @param list
	 * @return
	 */
	public static String toJson(Object src) {
		return JSON.toJSONString(src);
	}
	
	/**
	 * 
	 * 描述：将json转化为对象:String--->Object
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object parseJsonObject(String json,Class<?> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * 
	 * 描述：将json转化为对象:String--->List<?>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static List<?> parseJsonArray(String json,Class<?> clazz) {
		return JSON.parseArray(json, clazz);
	}
	
	/**
	 * 
	 * 描述：将json转化为Map:String--->Map<?>
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json){
		return JSON.parseObject(json, Map.class);
	}
	  

}
