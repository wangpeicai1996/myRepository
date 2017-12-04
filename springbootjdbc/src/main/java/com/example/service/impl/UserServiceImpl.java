package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.IUserDao;
import com.example.entity.User;
import com.example.service.IUserService;
import com.example.vo.Result;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	
	
	@Override
	public Result findAllUser() {
		List<User> users=this.userDao.findUser();
		Result result=new Result();
		if(users!=null&&users.size()>0) {
			result.setStatus(0);
			result.setData(users);
		}else {
			result.setStatus(1);
			result.setMessage("查询失败");
		}
		return result;
	}

	@Override
	public Result addUser(User user) {
		Result result=new Result();
		int rowAffect=this.userDao.addUser(user);
		if(rowAffect==1) {
			result.setStatus(0);
			result.setMessage("添加成功");
		}else {
			result.setStatus(1);
			result.setMessage("添加失败");
		}
		return result;
	}

	@Override
	public Result updateUserById(User user) {
		Result result = new Result();
		int rowAffect=this.userDao.updateUserById(user);
		if(rowAffect==1) {
			result.setStatus(0);
			result.setMessage("更新成功");
		}else {
			result.setStatus(1);
			result.setMessage("更新失败");
		}
		return result;
	}

	@Override
	public Result deleteUserById(int id) {
		Result result=new Result();
		int rowAffect=this.userDao.deleteUserById(id);
		if(rowAffect==1) {
			result.setStatus(0);
			result.setMessage("删除成功");
		}else {
			result.setStatus(1);
			result.setMessage("删除失败");
		}
		return result;
	}
	
}
