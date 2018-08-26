package com.pro.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.pro.constant.Constant;
import com.pro.domain.User;
import com.pro.service.UserService;
import com.pro.service.impl.UserServiceImpl;
import com.pro.utils.UUIDUtils;


/**
 * 用户模块
 */
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String registerUI(HttpServletRequest request , HttpServletResponse response) {
		return  "/jsp/register.jsp";
	}
	/**
	 * 购物车跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cartUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/cart.jsp";
	}
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取数据封装对象
			User user=new User();
			user.setUid(UUIDUtils.getId());
			user.setState(Constant.USER_IS_ACTIVE);
			user.setCode(UUIDUtils.getCode());
			BeanUtils.populate(user, request.getParameterMap());
			if("0".equals(user.getSex())) {
				user.setSex("女");
			}else {
				user.setSex("男");
			}
			//2.调用service方法
			UserService us= new UserServiceImpl();
			us.register(user);
			//3.注册成功的提示信息
			request.setAttribute("msg", "恭喜你，注册成功！");
		} catch (Exception e) {
			e.printStackTrace();
			//转发到msg.jsp
			request.setAttribute("msg", "用户注册失败!");
			return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}

	//用户激活
//	public String active(HttpServletRequest request,HttpServletResponse response) {
//		try {
//			//1.接受code
//			String code=request.getParameter("code");
//			//2.调用service方法获取数据，返回user
//			UserService us=new UserServiceImpl();
//			User user=us.active(code);
//			
//			//3.判断code是否正确
//			if(user==null) {
//				//没找到用户
//				request.setAttribute("msg", "激活失败，请重新激活");
//				return "/jsp/msg.jsp";
//			}else {
//				//激活成功
//				request.setAttribute("msg", "恭喜你激活成功，可以登陆了");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			request.setAttribute("msg", "激活失败，请重新激活");
//			return "/jsp/msg.jsp";
//		}
//		return "/jsp/msg.jsp";
//	}
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String loginUI(HttpServletRequest request , HttpServletResponse response) {
		return  "/jsp/login.jsp";
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public  String login(HttpServletRequest request , HttpServletResponse response) {
		try {
			//1.获取用户和密码
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//验证码
			//获取生成的验证码
			HttpSession session=request.getSession();
			String code=session.getAttribute("code").toString();
			//获取输入的验证码
			String validataCode=request.getParameter("validatacode");
			if(validataCode.equalsIgnoreCase(code)) {
			//2.调用service方法
			UserService us=new UserServiceImpl();
			User user=us.login(username,password);
			if(user==null) {
				//用户名或密码错误
				request.setAttribute("msg", "用户名或密码错误");
				return "/jsp/login.jsp";
			}else if(user!=null&&user.getState()==1){
				//用户不为空，判断是否激活
				/*if(user.getState()==Constant.USER_IS_NOT_ACTIVE) {
					request.setAttribute("msg", "此账户未激活，请激活后登录");
					return "/jsp/msg.jsp";
				}else {*/
					//登陆成功，保存用户信息在session里面
					request.getSession().setAttribute("user", user);
		/////////////////////////////////////////////////////////////////
				/*记住用户名是否勾选*/
		////////////////////////////////////////////////////////////////			
					if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
						Cookie cookie=new Cookie("saveName",URLEncoder.encode(username, "utf-8"));//设置中文编码
						cookie.setMaxAge(Integer.MAX_VALUE);
						cookie.setPath(request.getContextPath());
						response.addCookie(cookie);
					}
				
					//登陆成功后跳转到首页
					response.sendRedirect(request.getContextPath());
				}else {
					request.setAttribute("msg", "用户状态不可用");
					return "/jsp/login.jsp";
				}
			}else {
				request.setAttribute("check", "验证码错误");
				return "/jsp/login.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "用户登陆失败");
			return "/jsp/msg.jsp";
		}
		return null;
	}

	  
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
