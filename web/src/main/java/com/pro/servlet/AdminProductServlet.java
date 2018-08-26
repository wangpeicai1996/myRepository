package com.pro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.domain.Category;
import com.pro.domain.Product;
import com.pro.service.CategoryService;
import com.pro.service.ProductService;
import com.pro.utils.BeanFactory;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	
	/**
	 * 展示所有已上架商品
	 * @param request
	 * @param response
	 * @param String 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service
			ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
			String state=request.getParameter("state");
			List<Product> list=ps.findAll(state);
			//2.将list放入request，请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询商品失败");
			return "/admin/msg.jsp";
		}
		return "/admin/product/list.jsp";
	}

	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//调用categoryservice查询所有分类
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			request.setAttribute("list", cs.findList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "添加商品失败");
			return "/admin/msg.jsp";
		}
		return "/admin/product/add.jsp";
	}
	
	/**
	 * 编辑商品
	 */
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pid=request.getParameter("pid");
			ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
			Product p=ps.getById(pid);
			request.setAttribute("p", p);
			//调用service获取所有的分类
			CategoryService cs=(CategoryService)BeanFactory.getBean("CategoryService");
			List<Category> list=cs.findList();
			//将返回值转发到request域中
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/edit.jsp";
	}

	
	/**
	 * 删除商品
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取商品id和图片路径
			String pid=request.getParameter("pid");
			String imageUrl=request.getParameter("pimage");
			//获取图片文件完整路径
			String pimage=request.getServletContext().getRealPath(imageUrl);
			ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
			ps.delete(pid,pimage);
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll&state=onsale");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除商品失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
}
