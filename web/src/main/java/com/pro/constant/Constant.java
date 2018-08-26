package com.pro.constant;

public interface Constant {
	//用户未激活
	int USER_IS_NOT_ACTIVE=0;
	
	//用户已激活
	int USER_IS_ACTIVE=1;
	
	//记住用户名
	String SAVE_NAME="ok";
	
	//热门商品
	int PRODUCT_IS_HOT=1;
	
	//商品未下架
	int PRODUCT_IS_UP=0;
	
	//商品已下架
	int PRODUCT_IS_DOWN=1;
	
	//商品分页显示条数
	int PRODUCT_PAGESIZE = 12;
	
	//订单分页显示条数
	int ORDER_PAGESIZE=3;;
	
	//订单未付款
	int ORDER_UNPAY=0;
	
	//订单已付款
	int ORDER_PAYED=1;
	
	//订单已发货
	int ORDER_YIFAHUO=2;

	//订单未发货
	int ORDER_WEIFAHUO=3;
	//订单已完成
	int ORDER_FINISH=4;
	
	//收藏分页显示条数
	int COLLECTION_PAGESIZE=3;
	
	//用户管理页面显示条数
	int USER_PAGESIZE=5;
	
}
