package com.pro.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pro.dao.UserDao;
import com.pro.domain.Page;
import com.pro.domain.User;
import com.pro.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{

	/**
	 * 用户注册
	 * @throws SQLException 
	 */
	@Override
	public void save(User user) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),
				user.getState(),user.getCode());
	}

	/**
	 * 通过激活码获取用户,用于用户激活
	 * @throws SQLException 
	 */
//	@Override
//	public User getByCode(String code) throws SQLException {
//		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
//		String sql="select * from user where code=? limit 1";
//		return qr.query(sql,new BeanHandler<>(User.class),code);
//	}
/**
 * 更新用户
 * @throws SQLException 
 */
	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());	
		String sql="update user set password =?,birthday=?,sex=?,state=?,code=? where uid=?";
		qr.update(sql,user.getPassword(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	
	}
/**
 * 用户登录查询
 */
@Override
public User getByUsernameAndPwd(String username, String password) throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	String sql="select * from user where username=? and password=? limit 1";
	return qr.query(sql, new BeanHandler<>(User.class),username,password);
}

/**
 * 查询所有用户
 */
@Override
public List<User> findAllUser(Page page) throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	String sql="select * from user limit ?,?";
	return qr.query(sql, new BeanListHandler<>(User.class),page.getStartIndex(),page.getPageSize());
}

/**
 * 后台获取用户总记录数
 */
@Override
public int getUserCount() throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	String sql="select count(*) from user ";
	return  ((Long)qr.query(sql, new ScalarHandler())).intValue();
}

/**
 * 通过用户id查询用户
 */
@Override
public User getUserById(String uid) throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	String sql="select * from user where uid=?";
	return qr.query(sql, new BeanHandler<>(User.class),uid);
	
}

/**
 * 保存编辑用户
 */
@Override
public int saveEdit(User user) throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	int result=0;
	try {
		DataSourceUtils.startTransaction();
		String sql="update user set username=?,name=?,password=?,email=?,telephone=?,state=? where uid=?";
		result=qr.update(sql,user.getUsername(),user.getName(),user.getPassword(),
				user.getEmail(),user.getTelephone(),user.getState(),user.getUid());
		DataSourceUtils.commitAndClose();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		DataSourceUtils.rollbackAndClose();
	}
	return result;
}

/**
 * 删除用户
 */
@Override
public int deleteUserById(String uid) throws Exception {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	int result=0;
	try {
		DataSourceUtils.startTransaction();
		String sql="delete from user where uid=?";
		result=qr.update(sql,uid);
		DataSourceUtils.commitAndClose();
		return result;
	} catch (Exception e) {
		e.printStackTrace();
		DataSourceUtils.rollbackAndClose();
	}
	return 0;
}

}
