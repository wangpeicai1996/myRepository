package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.service.IUserService;

@Service
public class userServiceImpl implements IUserService{

	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUser() {
		List<User> users = this.userMapper.findAllUser();
		if(users!=null&&users.size()>0) {
			return users;
		}else {
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,timeout=30000,rollbackFor=Exception.class)
	public int addUser(User user) {
		return this.userMapper.addUser(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,timeout=30000,rollbackFor=Exception.class)
	public int updateUserById(User user) {
		return this.userMapper.updateUserById(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,timeout=30000,rollbackFor=Exception.class)
	public int deleteUserById(int id) {
		return this.userMapper.deleteUserById(id);
	}

}
