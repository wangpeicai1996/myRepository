package com.example.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.entity.User;


public class UserMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg) throws SQLException {
		List<User> users=new ArrayList<User>();
		
		User user=new User();
		user.setId(rs.getInt("id"));
		user.setUserName(rs.getString("username"));
		user.setUserPassword(rs.getString("userpassword"));
		user.setAge(rs.getInt("age"));
		user.setSex(rs.getString("sex"));
		users.add(user);
		
		return users;
	}

	
}
