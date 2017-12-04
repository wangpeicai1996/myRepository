package com.example.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.dao.IUserDao;
import com.example.entity.User;


@Repository
public class UserDaoImpl implements IUserDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> findUser() {
		List<User> users=new ArrayList<User>();
		String sql="select * from t_user";
		RowMapper rm=new UserMapper();
		users=this.jdbcTemplate.query(sql, rm);
		System.out.println(users);
		return users;
	}

	@Override
	public int addUser(User user) {
		String sql="insert into t_user(username,userpassword,age,sex) values(?,?,?,?)";
		Object[] params=new Object[]{user.getUserName(),user.getUserPassword(),user.getAge(),user.getSex()};
		int rowAffect=this.jdbcTemplate.update(sql, params);
		return rowAffect;
	}

	@Override
	public int updateUserById(User user) {
		String sql="update t_user set username=?,userpassword=?,age=?,sex=? where id=?";
		Object[] params= {user.getUserName(),user.getUserPassword(),user.getAge(),user.getSex(),user.getId()};
		int rowAffect=this.jdbcTemplate.update(sql, params);
		return rowAffect;
	}

	@Override
	public int deleteUserById(int id) {
		String sql="delete from t_user where id=?";
		int rowAffect=this.jdbcTemplate.update(sql, id);
		return rowAffect;
	}

}
