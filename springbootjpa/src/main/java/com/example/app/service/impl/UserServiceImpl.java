package com.example.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.entity.User;
import com.example.app.repsitory.IUserRepsitory;
import com.example.app.service.IUserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepsitory userRepsitory;

	@Override
	public List<User> findAllUser() {
		List<User> users=this.userRepsitory.findAll();
		if(users!=null&&users.size()>0) {
			return users;
		}else {
			return null;
		}
	}

	@Override
	public int addUser(User user) {
		User user1=this.userRepsitory.save(user);
		if(user1!=null) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int updateUserById(User user) {
		User user1=this.userRepsitory.save(user);
		if(user1!=null) {
			return 1;
		}else {
			return 0;
		}
		
	}

	@Override
	public int deleteUserById(int id) {
		this.userRepsitory.delete(id);
		User user=this.userRepsitory.findOne(id);
		if(user==null) {
			return 1;
		}else {
			return 0;
		}
	}

}
