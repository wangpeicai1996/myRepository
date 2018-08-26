package com.pro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.domain.Category;
import com.pro.domain.Page;
import com.pro.domain.User;
import com.pro.service.CategoryService;
import com.pro.service.UserService;
import com.pro.utils.BeanFactory;
import com.pro.utils.UUIDUtils;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * 展示所有用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int pageNumber=new Integer(request.getParameter("pageNumber")).intValue();
			//1.调用service获取所有的用户
			UserService us=(UserService)BeanFactory.getBean("UserService");
			Page page =us.findAllUser(pageNumber);
			if(page.getData()!=null&&page.getData().size()>0) {
				request.setAttribute("page", page);
			}else {
				request.setAttribute("msg", "没有用户");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询用户失败");
			return "/admin/msg.jsp";
		}
		
		return "/admin/user/list.jsp";
	}
	

	
	/**
	 * 编辑用户
	 * @throws Exception 
	 */
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uid=request.getParameter("uid");
		UserService us=(UserService)BeanFactory.getBean("UserService");
		User user=us.getUserById(uid);
		if(user!=null) {
			request.setAttribute("user", user);
		}else {
			request.setAttribute("msg", "获取用户失败");
		}
		return "/admin/user/edit.jsp";
	}
	
	
	/**
	 * 保存编辑
	 */
	public String saveEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uid=request.getParameter("uid");
			String username=request.getParameter("username");
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String telephone=request.getParameter("telephone");
			int state=Integer.parseInt(request.getParameter("state"));
			User user =new User();
			user.setUid(uid);
			user.setUsername(username);
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setTelephone(telephone);
			user.setState(state);
			UserService us=(UserService)BeanFactory.getBean("UserService");
			int result=us.saveEdit(user);
			if(result>0) {
				//重定向
				response.sendRedirect(request.getContextPath()+"/adminUser?method=findAllUser&pageNumber=1");
			}else {
				request.setAttribute("msg", "编辑用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "编辑用户失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
	
	/**
	 * 删除用户
	 */
	public String deleteUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取输入的名称,封装Category对象
			String uid=request.getParameter("uid");
			//2.调用service
			UserService us=(UserService)BeanFactory.getBean("UserService");
			int result=us.deleteUserById(uid);
			if(result>0) {
				//重定向
				response.getWriter().write("success");
			}else {
				response.getWriter().write("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除用户失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
}
