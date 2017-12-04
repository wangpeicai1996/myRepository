package com.example.service;

import com.example.entity.User;
import com.example.vo.Result;

public interface IUserService {
	public Result findAllUser();
	public Result addUser(User user);
	public Result updateUserById(User user);
	public Result deleteUserById(int id);
}
