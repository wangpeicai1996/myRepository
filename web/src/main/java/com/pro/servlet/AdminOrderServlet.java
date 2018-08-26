package com.pro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.constant.Constant;
import com.pro.domain.Order;
import com.pro.domain.OrderItem;
import com.pro.domain.Page;
import com.pro.service.OrderService;
import com.pro.utils.BeanFactory;
import com.pro.utils.JsonUtils;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * 后台按照状态查询订单列表
	 */
	public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取状态
			String state=request.getParameter("state");
			int pageNumber=new Integer(request.getParameter("pageNumber")).intValue();
			//2.调用service
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			
			Page page =os.findAllByState(state,pageNumber);
			//3.请求转发
			request.setAttribute("page", page);
			request.setAttribute("state", state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询订单失败");
			return "/admin/msg.jsp";
		}
		return "/admin/order/list.jsp";
	}
	
	
	/**
	 * 订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf8");
			//1.获取订单oid
			String oid=request.getParameter("oid");
			
			//2.调用service
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			System.out.println("Order="+order.toString());
			//3.获取订单项列表，转换成json写回浏览器
			if(order!=null) {
				List<OrderItem> list=order.getItems();
				if(list!=null&&list.size()>0) {
					System.out.println("orderJson="+JsonUtils.Object2Json(order));
					response.getWriter().println(JsonUtils.Object2Json(order));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询订单详情失败");
			return "/admin/msg.jsp";
		}
				
		return null;
	}
	

	/**
	 * 修改订单状态
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String oid=request.getParameter("oid");
			System.out.println("获取的oid="+oid);
			int state=new Integer(request.getParameter("state")).intValue();
			//2.调用service，获取订单
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			//3.设置订单状态
			if(state==Constant.ORDER_FINISH) {
				order.setState(Constant.ORDER_FINISH);
				response.sendRedirect(request.getContextPath()+"/order?method=findMyOrdersByPage&pageNumber=1");
			}
			if(state==Constant.ORDER_YIFAHUO) {
				order.setState(Constant.ORDER_YIFAHUO);
				response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=2&pageNumber=1");
			}
			os.update(order);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "修改订单状态失败");
			return "/admin/msg.jsp";
		}
		return null;
	}
}
