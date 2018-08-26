package com.pro.service;

import com.pro.domain.Page;
import com.pro.domain.User;

public interface UserService {

	void register(User user) throws Exception ;

	//User active(String code)throws Exception ;

	User login(String username, String password)throws Exception;
	
	Page<User> findAllUser(int pageNumber) throws Exception;

	User getUserById(String uid)throws Exception;

	int saveEdit(User user)throws Exception;

	int deleteUserById(String uid)throws Exception;

}
