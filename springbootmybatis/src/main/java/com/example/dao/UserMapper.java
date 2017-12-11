package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.User;

@Mapper
public interface UserMapper {
	public List<User> findAllUser();
	public int addUser(User user);
	public int updateUserById(User user);
	public int deleteUserById(int id);
}
