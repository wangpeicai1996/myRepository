package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface IUserService {
	
	public List<User> findAllUser();
	public int addUser(User user);
	public int updateUserById(User user);
	public int deleteUserById(int id);
}
