package com.pro.service.impl;


import java.util.List;

import com.pro.constant.Constant;
import com.pro.dao.UserDao;
import com.pro.domain.Order;
import com.pro.domain.Page;
import com.pro.domain.User;
import com.pro.service.UserService;
import com.pro.utils.BeanFactory;

public class UserServiceImpl implements UserService{
	private UserDao ud=(UserDao)BeanFactory.getBean("UserDao");
	/**
	 * 用户注册
	 */
	@Override
	public void register(User user) throws Exception {
		//1.调用dao操作数据库
		ud.save(user);
		//发送邮件
		//String emailMsg="恭喜"+user.getUsername()+"成为我们商城的一员,<a href='http://locahost/web/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		//MailUtils.sendMail(user.getEmail(), emailMsg);
	}
	/**
	 * 用户激活
	 * @throws Exception 
	 */
//	@Override
//	public User active(String code) throws Exception {
//		UserDao ud=new UserDaoImpl();
//		
//		//通过code获取用户
//		User user=ud.getByCode(code);
//		if(user==null) {
//			//通过激活码没有找到用户
//		}
//		//若获取到了，就修改用户
//		user.setState(Constant.USER_IS_ACTIVE);
//		user.setCode(null);
//		ud.update(user);
//		return user;
//	}
	/**
	 * 用户登录
	 */
	@Override
	public User login(String username, String password) throws Exception {
		return ud.getByUsernameAndPwd(username,password);
	}
	
	/**
	 * 查询所有用户
	 */
	@Override
	public Page<User> findAllUser(int pageNumber) throws Exception {
		//1.创建page
				int pageSize=Constant.USER_PAGESIZE;
				Page<User> page=new Page<User>(pageNumber,pageSize);
				
				//2.查询总条数，设置总条数
				int taotalRecord=ud.getUserCount();
				page.setTotalRecord(taotalRecord);
				//3.查询当前页数据，设置当期页数据
				List<User> data=ud.findAllUser(page);
				page.setData(data);		
				return page;
	}
	
	/**
	 * 根据用户id查询用户
	 */
	@Override
	public User getUserById(String uid) throws Exception {
		User user=ud.getUserById(uid);
		if(user!=null) {
			return user;
		}
		return null;
	}
	
	/**
	 * 编辑保存用户
	 */
	@Override
	public int saveEdit(User user) throws Exception {
		return ud.saveEdit(user);
	}
	/**
	 * 删除用户
	 */
	@Override
	public int deleteUserById(String uid) throws Exception {
		
		return ud.deleteUserById(uid);
	}
	
	

}
