package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexContoller {
	
	@RequestMapping(value="/jsp",method=RequestMethod.GET)
	public String testJsp() {
		
		return "index";
	}
}
