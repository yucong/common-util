package com.java.util.sign;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.java.util.base.MD5;
import com.java.util.json.FastJsonUtil;


/**
 * 签名的的规则
 * 
 */
public class SignUtil {
	
	/**app请求签名的key*/
	public static final String APP_KEY = "b9b8309ca3a04a7c906c67f6ed24ec9d";
	
	
	/**
	 * 生成签名
	 * 
	 * @param bean
	 * @param key
	 * @return
	 */
	public static <T> String createSign(T bean, String key) {
		Map<String, Object> map = obj2Map(bean);
		String signValue = getSign(map,key);
		return signValue;
	}
	
	/**
	 * 生成sign 
	 */
	public static String getSign(Map<String, Object> map, String key) {
		String[] nameArr = map.keySet().toArray(new String[] {});
		// ASC码升序排序
		Arrays.sort(nameArr);
		StringBuilder builder = new StringBuilder();
		for (String name : nameArr) {
			if ("sign".equals(name))
				continue;
			Object value = map.get(name);
			
			if (value == null || value.toString().length() == 0 )
				continue;
			
			if(value instanceof String || isPrimitive(value)) {
				builder.append(name + "=" + value + "&");
			}
			
			// date类型签名时取时间戳
			else if(value instanceof Date) {
				long timestamp = ((Date)value).getTime();
				builder.append(name + "=" + timestamp + "&");
			} else {
				builder.append(name + "=" + FastJsonUtil.toJson(value) + "&");
			}
			
		}
		builder.append("key=" + key);
		String signStr = builder.toString().replaceAll(" ", "");
		System.out.println("生成的值："+signStr);
		return MD5.getMD5(signStr);
	}
	
	
	
	/**判断一个对象是否是基本类型或基本类型的封装类型*/
	private static boolean isPrimitive(Object obj) {
		try {
			return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 验证签名
	 * 
	 * @param bean
	 * @param key
	 * @param sign
	 * @return
	 */
	public static <T> boolean verifySign(T bean, String key,String sign) {
		Map<String, Object> map = obj2Map(bean);
		String signValue = getSign(map,key);
		System.out.println("signValue:" + signValue);
		return signValue.equals(sign);
	}
	
	
	/*
	 * 利用Java的反射将对象转换成Map
	 */
	private static Map<String,Object> obj2Map(Object obj)  {
		Map<String,Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for(Field field:fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			throw new RuntimeException("Java类转Map异常",e);
		}
		return map;
	}
	
	
	
	
	
	
	
}
