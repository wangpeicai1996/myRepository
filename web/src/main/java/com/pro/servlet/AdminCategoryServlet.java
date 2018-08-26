package com.pro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.domain.Category;
import com.pro.service.CategoryService;
import com.pro.utils.BeanFactory;
import com.pro.utils.UUIDUtils;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * 展示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service获取所有的分类
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			List<Category> list=cs.findList();
			
			//2.将返回值转发到request域中
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询分类失败");
			return "/admin/msg.jsp";
		}
		
		return "/admin/category/list.jsp";
	}
	/**
	 *跳转到添加页面 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/category/add.jsp";
	}

	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.封装Category对象
			Category c=new Category();
			c.setCid(UUIDUtils.getId());
			String cname=request.getParameter("cname");
			c.setCname(cname);
			//2.调用service完成添加
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			cs.save(c);
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "添加分类失败");
			return "/admin/msg.jsp";
		}
		return null;
	}

	
	/**
	 * 编辑分类
	 */
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=request.getParameter("cid");
		request.setAttribute("cid", cid);
		return "/admin/category/edit.jsp";
	}
	
	
	/**
	 * 编辑分类名称
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取输入的名称,封装Category对象
			Category c=new  Category();
			String cname=request.getParameter("cname");
			String cid=request.getParameter("cid");
			c.setCname(cname);
			c.setCid(cid);
			//2.调用service
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			cs.update(c);
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改分类失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
	
	/**
	 * 删除分类
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取输入的名称,封装Category对象
			Category c=new  Category();
			String cid=request.getParameter("cid");
			c.setCid(cid);
			//2.调用service
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			cs.delete(c);
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除分类失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
}
