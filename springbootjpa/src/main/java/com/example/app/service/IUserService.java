package com.example.app.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.app.entity.User;

public interface IUserService extends Repository<User,Integer>{

	public List<User> findAllUser();
	public int addUser(User user);
	public int updateUserById(User user);
	public int deleteUserById(int id);
}
