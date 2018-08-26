package com.pro.filter;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	String passUrl="";
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;	
		String [] passUrls=passUrl.split(";");
		for(String url : passUrls) {
			if(httpRequest.getRequestURI().contains(url)) {
				//包含可以访问的网页，直接放行
				chain.doFilter(httpRequest, httpResponse);
				return;
			}else {
				//必须登录以后才能访问的网页，判断是否登录
				HttpSession session=httpRequest.getSession();
				if(session.getAttribute("user")!=null) {
					//如果已登录，放行
					chain.doFilter(httpRequest, httpResponse);
				}else {
					//如果未登录，跳转到登录页面
					//httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/login.jsp");
					httpRequest.setAttribute("msg", "请先登录");
					httpRequest.getRequestDispatcher("/jsp/msg.jsp").forward(httpRequest, httpResponse);
					return;
				}
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//获取要排除的url值
		passUrl=fConfig.getInitParameter("passUrl");
	}

}
