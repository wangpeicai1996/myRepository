package com.tarena.dao;

import java.util.List;

import com.tarena.entity.User;
import com.tarena.entity.UserRole;
import com.tarena.vo.Page;

public interface UserMapper {
	public String login(User user);
	//分页(全部用户)
	public int getCount(Page page);
	public List<User> getUsers(Page page);
	
	//分页(指定具体角色)
	public int getCountByRole(Page page);
	public List<User> getUsersByRole(Page page);
	//删除
	public int deleteUser(String userId);
	public int deleteUserRole(String userId);
	//删除指定用户id的视频和缓存
	public int deleteHistoryCacheByUserId(String userId);
	
	//根据用户id查询用户信息
	public User findUserById(String userId);
	//添加用户
	public int addUser(User user);
	//添加用户和角色中间表
	public int addUserRole(UserRole ur);
	//添加用户信息
	public int updateUser(User user);
	
	//查询所有数据
	public List<User> findAllUsers();
	
}
