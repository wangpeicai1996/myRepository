package com.example.dao;

import java.util.List;

import com.example.entity.User;

public interface IUserDao {
	public List<User> findUser();
	public int addUser(User user);
	public int updateUserById(User user);
	public int deleteUserById(int id);
}
