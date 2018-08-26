package com.pro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.domain.Admin;
import com.pro.service.AdminService;
import com.pro.utils.BeanFactory;

/**
 * Servlet implementation class AdminIndexServlet
 */
public class AdminIndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 后台管理登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Admin admin=new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			AdminService as=(AdminService) BeanFactory.getBean("AdminService");
			Admin result=as.login(admin);
			System.out.println(result);
			if(result!=null&&1==result.getPermission()) {
				request.getSession().setAttribute("admin", result);
				request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
			}else {
				request.setAttribute("msg","你不是管理员，无法登陆" );
				return "/admin/index.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			return "/admin/index.jsp";
		}
		return null;
	}
}
