package com.tarena.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.tarena.entity.User;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface IUserService {
	//登录
	public boolean login(User user);
	//分页
	public Result findUsersByPage(Page page);
	
	//删除
	public Result deleteUser(String userId);
	//根据用户id查询用户信息
	public Result findUserById(String userId);
	//添加用户
	public Result addUser(User user,String roleId,MultipartFile addPicture,HttpServletRequest request,HttpServletResponse response);
	
	//修改用户
	public Result updateUser(User user,String[] roleIds,MultipartFile updatePicture,HttpServletRequest request,HttpServletResponse response);
	
	//导出数据到excel中
	public byte[] exportUser();
	
}
