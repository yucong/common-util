package com.java.util.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
		

public class StringUtil {

	/**
     * 判断一个字符串是否为null或空值.
     *
     * @param str 		指定的字符串
     * @return 			
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
	 * 判断是否不是空
	 * 
     * @param str 		指定的字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return str!=null && str.trim().length()!=0;
	}

    /**
	  * 是否是纯数字.
	  *
	  * @param str 		指定的字符串
	  * @return 		
	  */
	public static boolean isNumber(String str) {
		boolean isNumber = false;
		String expr = "^[0-9]+$";
		if (str.matches(expr)) {
			isNumber = true;
		}
		return isNumber;
	}
 	
 	/**
	  * 是否只是字母和数字.
	  *
	  * @param str 		指定的字符串
	  * @return 
	  */
 	public static boolean isNumberLetter(String str) {
 		boolean isNoLetter = false;
 		String expr = "^[A-Za-z0-9]+$";
 		if (str.matches(expr)) {
 			isNoLetter = true;
 		}
 		return isNoLetter;
 	}
 	
 	/**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
 	public static boolean isMobileNo(String str) {
 		boolean isMobileNo = false;
 		try {
 			//13开头
			//Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
 			Pattern p = Pattern.compile("^(13|14|15|17|18)\\d{9}$");
			Matcher m = p.matcher(str);
			isMobileNo = m.matches();
		} catch (Exception e) {
			
		}
 		return isMobileNo;
 	}
 	
    /**
	  * 从输入流中获得String.
	  *
	  * @param is 输入流
	  * @return 获得的String
	  */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			//最后一个\n删除
			if(sb.indexOf("\n")!=-1 && sb.lastIndexOf("\n") == sb.length()-1){
				sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n")+1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
     * 根据指定的字符把源字符串分割成一个数组  
     *   
     * @param src  
     * @return  
     */ 
    public static List<String> parseString2ListByCustomerPattern(String pattern, String src) {  
        if (src == null) {
        	 return null;  
        }  
        List<String> list = new ArrayList<String>();  
        String[] result = src.split(pattern);  
        for (int i = 0; i < result.length; i++) {  
            list.add(result[i]);  
        }  
        return list;  
    }
    
    /**
     * 将字符串转化为int
     * 
     * @param s
     * @return
     */
    public static int toInt(String s) {  
    	int result = 0;
    	if (!isEmpty(s)) {  
            try {  
            	result = Integer.parseInt(s);  
            } catch (Exception e) {  
                // 非数值字符串默认返回0
            }  
        }  
        return result;  
    } 
    
    

    /**
     * 将字符串转化为long
     * 
     * @param s
     * @return
     */
    public static long toLong(String s) {  
    	long result = 0;
    	if (!isEmpty(s)) {  
            try {  
            	result = Long.parseLong(s);  
            } catch (Exception e) {  
                
            }  
        }  
        return result;  
    }  
    
    /**
     * 将字符串转化为float
     * 
     * @param s
     * @return
     */
    public static Float toFloat(String s) {  
    	float result = new Float(0);//0.00
    	if (!isEmpty(s)) {  
            try {  
            	result = Float.parseFloat(s);  
            } catch (Exception e) {  
                
            }  
        }  
        return result;  
    }  
    
    /**
     * 将字符串转化为double
     * 
     * @param s
     * @return
     */
    public static double toDouble(String s) {  
    	double result = 0;
    	if (!isEmpty(s)) {  
    		try {  
            	result = Double.parseDouble(s);  
            } catch (Exception e) {  
                
            }  
        }  
        return result;  
    }
    
    public static String getUUID() {
    	return UUID.randomUUID().toString();
    }
    
    public static String getSimpleUUID() {
    	return UUID.randomUUID().toString().replace("-", SymbolicConsts.EMPTY_STR);
    }
    
    /**
	 * 格式化模糊查询
	 */
	public static String formatLike(String str){
		String result = null;
		if(isNotEmpty(str)){
			result = SymbolicConsts.PERCENT_MARK + str + SymbolicConsts.PERCENT_MARK;
		} 
		return result;
	}
	
	
	/**
	 * 过滤emoji表情
	 * @author 喻聪
	 * @date   2018-05-14
	 *  
	 */
	public static String filterEmoji(String source) { 
        if(source  == null) return null;
        Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
        Matcher emojiMatcher = emoji.matcher(source);
        if ( emojiMatcher.find()) {
        	source = emojiMatcher.replaceAll(SymbolicConsts.EMPTY_STR);//过滤掉emoiji表情符
        } 
       return source; 
    }
	
	/** 
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0 
	 * @param version1 
	 * @param version2 
	 * @return 
	 */  
	public static int compareVersion(String version1, String version2) throws RuntimeException {  
	    if (version1 == null || version2 == null) {  
	        throw new RuntimeException("compareVersion error:illegal params.");  
	    }  
	    String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；  
	    String[] versionArray2 = version2.split("\\.");  
	    int idx = 0;  
	    int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
	    int diff = 0;  
	    while (idx < minLength  
	            && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
	            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
	        ++idx;  
	    }  
	    //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
	    diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
	    return diff;  
	}
}
