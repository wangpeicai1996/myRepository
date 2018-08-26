package com.pro.dao;

import java.util.List;

import com.pro.domain.Order;
import com.pro.domain.OrderItem;
import com.pro.domain.Page;

public interface OrderDao {

	void save(Order order) throws Exception;

	void saveItem(OrderItem oi)throws Exception;

	int getTotalRecord(String uid)throws Exception;

	List<Order> findMyOrdersByPage(Page<Order> page, String uid)throws Exception;

	Order getById(String oid)throws Exception;

	void update(Order order)throws Exception;

	List<Order> findAllByState(String state,Page page)throws Exception;

	int getAdminTotalRecord(String state) throws Exception;

	int deleteOrder(String oid) throws Exception;;
	
}
