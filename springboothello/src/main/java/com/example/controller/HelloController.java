package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello() {
		return "hello springboot";
	}
	
	@RequestMapping(value="/exception",method=RequestMethod.GET)
	public int globalException() {
		int a=5/0;//构建一个除数为0的异常
		return a;
	}
}
