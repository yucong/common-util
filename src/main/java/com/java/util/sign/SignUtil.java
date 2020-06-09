package com.java.util.sign;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.java.util.base.MD5;


/**
 * 签名的的规则
 * 
 */
public class SignUtil {
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		UserSign userSign = new UserSign();
		userSign.setId(1);
		userSign.setAddress("上海市");
		userSign.setEmail("16343@sina.cn");

		boolean result = verifySign(userSign, "key","sign");
		System.out.println("result:" + result);
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
		// System.out.println("signValue:" + signValue);
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
	
	
	/*
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
			if (map.get(name) == null || (map.get(name)).toString().length() == 0 )
				continue;
			builder.append(name + "=" + map.get(name) + "&");
		}
		builder.append("key=" + key);
		String signStr = builder.toString().replaceAll(" ", "");
		// System.out.println("生成的值："+signStr);
		return MD5.getMD5(signStr);
	}
	
	
	
	
}
