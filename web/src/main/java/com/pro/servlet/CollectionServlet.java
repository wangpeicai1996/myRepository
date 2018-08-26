package com.pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.constant.Constant;
import com.pro.domain.Product;
import com.pro.domain.User;
import com.pro.service.CollectionService;
import com.pro.service.OrderService;
import com.pro.utils.BeanFactory;
public class CollectionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String addCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		User user=(User) request.getSession().getAttribute("user");
		PrintWriter pw=response.getWriter();
			try {
				if(user!=null) {//判断是否登录
					if(pid!=null) {//判断商品pid是否获取
						String uid=user.getUid();
						CollectionService cs=(CollectionService) BeanFactory.getBean("CollectionService");
						String result=cs.addCollection(pid, uid);
						if("1".equals(result)) {
							pw.write("success");
						}else if("2".equals(result)){
							pw.write("error");
						}else if("3".equals(result)) {
							pw.write("repeat");
						}
					}else {
						pw.write("fail");
					}
				}else if(user==null) {
					request.setAttribute("msg", "请先登录！");
					pw.write("login");
				}
			}finally {
				pw.close();
			}
		return null;
	}
	
	public String findMyCollectionsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pageNumber,设置pageSize,获取userid
			int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=Constant.COLLECTION_PAGESIZE;
			User user=(User)request.getSession().getAttribute("user");
			if(user==null) {
				//未登录
				request.setAttribute("msg", "请先登录！");
				return "/jsp/msg.jsp";
			}
			//2.调用service获取当前页的所有数据pagebean
			CollectionService os=(CollectionService)BeanFactory.getBean("CollectionService");
			List<Product> productList=os.findMyCollectionsByPage(pageNumber, pageSize, user.getUid());
			//3.将productList放入request域中，请求转发collection_list.jsp
			request.setAttribute("product", productList);
			System.out.println("productList="+productList.toString());
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "获取我的收藏失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/collection_list.jsp";
	}
	
	/**
	 * 删除收藏
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		User user=(User) request.getSession().getAttribute("user");
		String uid=user.getUid();
		CollectionService cs=(CollectionService)BeanFactory.getBean("CollectionService");
		int result=cs.deleteCollection(pid,uid);
		System.out.println(result);
		PrintWriter pw=response.getWriter();
		if(result>0) {
			pw.write("success");
			pw.close();
		}
		pw.write("error");
		return super.index(request, response);
	}
	
}
