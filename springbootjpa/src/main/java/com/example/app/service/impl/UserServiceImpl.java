package com.example.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.entity.User;
import com.example.app.service.IUserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService{
		

	
	@Override
	public List<User> findAllUser() {
		
		return null;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserById(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
