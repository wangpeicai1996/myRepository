package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public List<User> findAllUser(){
		List<User> users=new ArrayList<User>();
		users=this.userService.findAllUser();
		return users;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addUser(User user){
		int rowAffect=this.userService.addUser(user);
		if(rowAffect==1) {
			return "添加成功";
		}else {
			return "添加失败";
		}
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateUserById(User user){
		int rowAffect=this.userService.updateUserById(user);
		if(rowAffect==1) {
			return "更新成功";
		}else {
			return "更新失败";
		}
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String deleteUserById(@Param("id") int id){
		int rowAffect=this.userService.deleteUserById(id);
		if(rowAffect==1) {
			return "删除成功";
		}else {
			return "删除失败";
		}
	}
	
	
	
	
}
