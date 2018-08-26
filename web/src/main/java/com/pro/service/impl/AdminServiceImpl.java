package com.pro.service.impl;

import com.pro.dao.AdminDao;
import com.pro.domain.Admin;
import com.pro.service.AdminService;
import com.pro.utils.BeanFactory;

public class AdminServiceImpl implements AdminService{

	private AdminDao ad=(AdminDao) BeanFactory.getBean("AdminDao");
	/**
	 * 后台登录
	 */
	public Admin login(Admin admin) throws Exception{
		return ad.login(admin);
	}

}
