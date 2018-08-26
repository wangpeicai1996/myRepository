package com.pro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.domain.Cart;
import com.pro.domain.CartItem;
import com.pro.domain.Product;
import com.pro.service.ProductService;
import com.pro.utils.BeanFactory;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跳转购物车页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String cartUI(HttpServletRequest request, HttpServletResponse response) {
		return  "/jsp/cart.jsp";
	}
	
    /**  
     * 加入购物车
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
	public String add2cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//1.获取pid,count
			String pid = request.getParameter("pid");
			int count=Integer.parseInt(request.getParameter("count"));
		//2.封装cartitem	
			//创建cartItem
			ProductService ps=(ProductService)BeanFactory.getBean("ProductService");
			Product product=ps.getById(pid);
			CartItem cartItem=new CartItem(product ,count);
		//3.将cartitem加入购物车
			//获取购物车
			Cart cart=getCart(request);
			cart.add2Cart(cartItem);
		//4.重定向
			response.sendRedirect(request.getContextPath()+"/cart?method=cartUI");
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("msg", "加入购物车失败");
		return "/jsp/msg.jsp";
	}
		return null;
	}
	
	private Cart getCart(HttpServletRequest request) {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
	/**
	 * 从购物车移除商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取商品的pid
		String pid=request.getParameter("pid");
		//2.获取购物车，执行移除
		getCart(request).removeFromCart(pid);
		//3.重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	
	/**
	 * 清空购物车
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取购物车
			getCart(request).clearCart();
		//2.重定向
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
}
