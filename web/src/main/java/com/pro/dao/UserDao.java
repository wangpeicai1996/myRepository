package com.pro.dao;


import java.util.List;

import com.pro.domain.Page;
import com.pro.domain.User;

public interface UserDao {

	void save(User user) throws Exception ;

	//User getByCode(String code) throws Exception;

	void update(User user) throws Exception;

	User getByUsernameAndPwd(String username, String password)throws Exception;

	List<User> findAllUser(Page page)throws Exception;
	
	int getUserCount()throws Exception;

	User getUserById(String uid)throws Exception;

	int saveEdit(User user)throws Exception;

	int deleteUserById(String uid)throws Exception;

}
