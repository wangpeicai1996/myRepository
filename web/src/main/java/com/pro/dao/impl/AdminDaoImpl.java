package com.pro.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.pro.dao.AdminDao;
import com.pro.domain.Admin;
import com.pro.utils.DataSourceUtils;

public class AdminDaoImpl implements AdminDao{

	/**
	 * 后台登录
	 */
	public Admin login(Admin admin) throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from admin where username=? and password=?";
		return qr.query(sql, new BeanHandler<>(Admin.class),admin.getUsername(),admin.getPassword());
	}

}
