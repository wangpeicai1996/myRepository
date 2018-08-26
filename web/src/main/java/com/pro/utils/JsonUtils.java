package com.pro.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * json字符串转换工具类
 * @author Administrator
 *
 */
public class JsonUtils {
	
	/**
	 * 将对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String Object2Json(Object obj) {
		String jsonList=JSON.toJSONString(obj);
		return jsonList;
	}
}
