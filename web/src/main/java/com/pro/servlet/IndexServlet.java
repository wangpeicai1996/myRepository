package com.pro.servlet;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.constant.Constant;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.service.ProductService;
import com.pro.service.impl.ProductServiceImpl;
import com.pro.utils.BeanFactory;

/**
 * 首页模块
 * @author Administrator
 *
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response)  {
		try {
			//1.调用productservice查询最新商品和热门商品
			ProductService ps = (ProductService)BeanFactory.getBean("ProductService");
			List<Product> hotList=ps.findHot();
			List<Product> newList=ps.findNew();
			//2.将两个list放入request对象中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jsp/index.jsp";
	}
	
	
}
