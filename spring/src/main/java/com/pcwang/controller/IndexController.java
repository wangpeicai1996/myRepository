package com.pcwang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcwang.domain.User_Info;
import com.pcwang.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UserService user;

	@RequestMapping(value= {"/","/index"},method=RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest req,HttpServletResponse resp) {
		String userId = "";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("username======="+username+" password========"+password);
		if(username != null && password != null) {
			 userId = user.login(username, password);
		}
		System.out.println(userId);
		return userId;
	}
	
}
