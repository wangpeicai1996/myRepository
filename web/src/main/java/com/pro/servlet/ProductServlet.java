package com.pro.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.constant.Constant;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.service.ProductService;
import com.pro.service.impl.ProductServiceImpl;

/**
 * 商品模块
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 查询单个商品
	 * @param request
	 * @param response
	 * @return
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response)  {
		try {
			//1.获取pid
			  String pid = request.getParameter("pid");
			//2.调用service获取单个商品对象
			  ProductService ps=new ProductServiceImpl();
			  Product pro=ps.getById(pid);
			 //3.将查出来的数据放入request中，转发到/jsp/product_info.jsp中
			 request.setAttribute("product", pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询单个商品失败");
			return "/jsp/msg.jsp";
		}
		  return "/jsp/product_info.jsp";
	}
	
	/**
	 * 分类商品分页展示
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			//1.获取pageNumber cid，设置pagesize
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=Constant.PRODUCT_PAGESIZE;
			String cid=request.getParameter("cid");
			String keyword=request.getParameter("keyword");
			//String keyword=java.net.URLEncoder.encode(keyword1, "UTF-8");
			//String keyword=new String(keyword1.getBytes(),"UTF-8");
			//2.调用service，返回page对象
			 ProductService ps=new ProductServiceImpl();
			Page<Product> page=ps.findByPage(pageNumber,pageSize,cid,keyword);
			//3.将page对象放入request转发
			if(keyword!=null&&page.getData().size()==0) {
				request.setAttribute("msg", "没有找到你要查询的商品");
				return "/jsp/msg.jsp";
			}else {
			request.setAttribute("page", page);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg",	"分页查询失败");
			return "/jsp/msg.jsp";
			
		}
		return "/jsp/product_list.jsp";
	}
	
	
}
