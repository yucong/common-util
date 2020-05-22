package com.java.util.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.java.util.json.FastJsonUtil;

/**
 * io输出json字符串
 * 
 * @author 喻聪
 * @date   2017-08-03
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object obj) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=utf-8");
			out = response.getWriter();
			out.println(FastJsonUtil.toJson(obj));
		} catch (IOException e) {
			System.err.println("io输出异常：" + e.getMessage());
		} finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
