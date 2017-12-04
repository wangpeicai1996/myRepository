package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.IUserService;
import com.example.vo.Result;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public Result findAllUser() {
		Result result =new Result();
		result=this.userService.findAllUser();
		return result;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUser(User user) {
		Result result=new Result();
		result=this.userService.addUser(user);
		return result;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Result updateUserById(User user) {
		Result result=new Result();
		result=this.userService.updateUserById(user);
		return result;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Result deleteUserById(int id) {
		Result result=new Result();
		result=this.userService.deleteUserById(id);
		return result;
	}
	
	
}
