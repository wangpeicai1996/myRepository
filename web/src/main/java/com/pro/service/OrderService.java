package com.pro.service;


import com.pro.domain.Order;
import com.pro.domain.Page;

public interface OrderService {

	void save(Order order)throws Exception;

	Page<Order> findOrdersByPage(int pageNumber, int pageSize, String uid)throws Exception;

	Order getById(String oid)throws Exception;

	void update(Order order)throws Exception;

	Page<Order> findAllByState(String state,int pageNumber)throws Exception;
	
	int deleteOrder(String oid)throws Exception;;

}
