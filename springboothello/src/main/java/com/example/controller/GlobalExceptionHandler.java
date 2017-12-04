package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕获异常类
 * @author Administrator
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody//拦截返回结果为json格式给客户端、
	@ExceptionHandler(RuntimeException.class)
	public Map<String,Object> globalException(){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("status","500");
		result.put("message", "系统错误，请稍后再试(全局捕获异常)");
		return result;
	}
}
