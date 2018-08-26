package com.pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.constant.Constant;
import com.pro.domain.Cart;
import com.pro.domain.CartItem;
import com.pro.domain.Order;
import com.pro.domain.OrderItem;
import com.pro.domain.Page;
import com.pro.domain.User;
import com.pro.service.OrderService;
import com.pro.utils.BeanFactory;
import com.pro.utils.PaymentUtil;
import com.pro.utils.UUIDUtils;
/**
 * 订单模块
 * @author Administrator
 *
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 保存订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//0.获取购物车,获取user
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			User user=(User)request.getSession().getAttribute("user");
			if(user==null) {
				//未登录
				request.setAttribute("msg", "请先登录");
				return "/jsp/msg.jsp";
			}
			//1.封装订单对象
			Order order = new Order();
			order.setOid(UUIDUtils.getId());
			order.setOrdertime(new Date());
			order.setTotal(cart.getTotal());
			order.setState(Constant.ORDER_UNPAY);
			order.setUser(user);
			//2.调用orderservice方法保存
			for(CartItem ci : cart.getCartItems()) {
				//封装成orderitem
				OrderItem oi=new OrderItem();
				oi.setItemid(UUIDUtils.getId());
				oi.setCount(ci.getCount());
				oi.setSubtotal(ci.getSubtotal());
				oi.setProduct(ci.getProduct());
				oi.setOrder(order);
				order.getItems().add(oi);
			}
			OrderService os=(OrderService)BeanFactory.getBean("OrderService");
			os.save(order);
			
			//3.清空购物车
			cart.clearCart();
			//4.请求转发到order_info.jsp
			request.setAttribute("order", order);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/order_info.jsp";
    }

	/**
	 * 删除订单
	 * @return
	 * @throws Exception 
	 */
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid=request.getParameter("oid");
		OrderService os=(OrderService)BeanFactory.getBean("OrderService");
		int result=os.deleteOrder(oid);
		System.out.println(result);
		PrintWriter pw=response.getWriter();
		if(result>0) {
			pw.write("success");
			pw.close();
			
		}
		pw.write("error");
	}
	
	/**
	 * 查询我的订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pageNumber,设置pageSize,获取userid
			int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=Constant.ORDER_PAGESIZE;
			User user=(User)request.getSession().getAttribute("user");
			if(user==null) {
				//未登录
				request.setAttribute("msg", "请先登录！");
				return "/jsp/msg.jsp";
			}
			//2.调用service获取当前页的所有数据pagebean
			OrderService os=(OrderService)BeanFactory.getBean("OrderService");
			Page<Order> page=os.findOrdersByPage(pageNumber,pageSize,user.getUid());
			//3.将pagebean放入request域中，请求转发order_list.jsp
			request.setAttribute("page", page);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "获取我的订单失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_list.jsp";
	}
	
	/**
	 * 获取订单详情
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String oid= request.getParameter("oid");
			//2.调用service查询单个订单
			OrderService os=(OrderService)BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			//3.请求转发
			request.setAttribute("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg",	 "查询订单详情失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	/**
	 * 在线支付
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取信息
			String address=request.getParameter("address");
			String name=request.getParameter("name");
			String telephone=request.getParameter("telephone");
			String oid=request.getParameter("oid");
			//2.调用service获取订单，修改更新订单
			OrderService os=(OrderService)BeanFactory.getBean("OrderService");
			Order order=os.getById(oid);
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			os.update(order);
			//3.拼接给第三方的url
			String pd_FrpId = request.getParameter("pd_FrpId");
			String p0_Cmd = "Buy";
			String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
			String p2_Order = oid;
			String p3_Amt = "0.01";//应该用order.getTotal()获取订单总价,此处为测试数据;
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
			// 第三方支付可以访问网址
			String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
			String p9_SAF = "";
			String pa_MP = "";
			String pr_NeedResponse = "1";
			// 加密hmac(数字签名) 需要密钥
			String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
					p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
					pd_FrpId, pr_NeedResponse, keyValue);

			
			//发送给第三方
			StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			sb.append("p0_Cmd=").append(p0_Cmd).append("&");
			sb.append("p1_MerId=").append(p1_MerId).append("&");
			sb.append("p2_Order=").append(p2_Order).append("&");
			sb.append("p3_Amt=").append(p3_Amt).append("&");
			sb.append("p4_Cur=").append(p4_Cur).append("&");
			sb.append("p5_Pid=").append(p5_Pid).append("&");
			sb.append("p6_Pcat=").append(p6_Pcat).append("&");
			sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sb.append("p8_Url=").append(p8_Url).append("&");
			sb.append("p9_SAF=").append(p9_SAF).append("&");
			sb.append("pa_MP=").append(pa_MP).append("&");
			sb.append("pd_FrpId=").append(pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac=").append(hmac);
			//4.重定向
			response.sendRedirect(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "支付失败");
			return "/jsp/msg.jsp";
		}
		return null;
	}

	/**
	 * 支付成功的回调
	 */
	public String callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取第三方发送过来的数据
			String p1_MerId = request.getParameter("p1_MerId");
			String r0_Cmd = request.getParameter("r0_Cmd");
			String r1_Code = request.getParameter("r1_Code");
			String r2_TrxId = request.getParameter("r2_TrxId");
			String r3_Amt = request.getParameter("r3_Amt");
			String r4_Cur = request.getParameter("r4_Cur");
			String r5_Pid = request.getParameter("r5_Pid");
			String r6_Order = request.getParameter("r6_Order");
			String r7_Uid = request.getParameter("r7_Uid");
			String r8_MP = request.getParameter("r8_MP");
			String r9_BType = request.getParameter("r9_BType");
			String rb_BankId = request.getParameter("rb_BankId");
			String ro_BankOrderId = request.getParameter("ro_BankOrderId");
			String rp_PayDate = request.getParameter("rp_PayDate");
			String rq_CardNo = request.getParameter("rq_CardNo");
			String ru_Trxtime = request.getParameter("ru_Trxtime");
			// 身份校验 --- 判断是不是支付公司通知你
					String hmac = request.getParameter("hmac");
					String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
							"keyValue");

					// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
					boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
							r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
							r8_MP, r9_BType, keyValue);
					if (isValid) {
						// 响应数据有效
						if (r9_BType.equals("1")) {
							// 浏览器重定向
							request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
							
						} else if (r9_BType.equals("2")) {
							// 服务器点对点 --- 支付公司通知你
							System.out.println("付款成功！");
							// 修改订单状态 为已付款
							// 回复支付公司
							response.getWriter().print("success");
						}
			//2.获取订单，修改订单状态
			OrderService os=(OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(r6_Order);
			//修改订单状态
			order.setState(1);
			//3.更新订单
			os.update(order);
			} else {
				// 数据无效
				System.out.println("数据被篡改！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "支付失败");
		}
		return "/jsp/msg.jsp";
	}
	
}
