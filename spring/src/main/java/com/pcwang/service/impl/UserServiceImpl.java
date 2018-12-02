package com.pcwang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwang.dao.User_Info_Mapper;
import com.pcwang.domain.User_Info;
import com.pcwang.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private User_Info_Mapper user;
	
	public String login(String username, String password) {
		User_Info resultUser = user.getUser(username, password);
		String userId = "";
		if(resultUser.getUserID() != null) {
			userId = resultUser.getUserID();
		}
		return userId;
	}
	
	
}
