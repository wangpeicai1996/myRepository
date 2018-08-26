package com.pro.service.impl;

import java.util.List;

import com.pro.constant.Constant;
import com.pro.dao.OrderDao;
import com.pro.domain.Order;
import com.pro.domain.OrderItem;
import com.pro.domain.Page;
import com.pro.service.OrderService;
import com.pro.utils.BeanFactory;
import com.pro.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService{
	private OrderDao od=(OrderDao)BeanFactory.getBean("OrderDao");
	/**
	 * 保存订单
	 */
	@Override
	public void save(Order order) throws Exception {
	try {
		//1.开启事务
		DataSourceUtils.startTransaction();
		
		//2.向orders表中插入一条
		od.save(order);
		//3.向orderitem中插入多条
		for(OrderItem oi:order.getItems()) {
			od.saveItem(oi);
		}
		//4.事务提交
		DataSourceUtils.commitAndClose();
	} catch (Exception e) {
		e.printStackTrace();
		DataSourceUtils.rollbackAndClose();
	}
		
	}
	/**
	 * 查询我的订单
	 */
	@Override
	public Page<Order> findOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		//1.创建page
		Page<Order> page=new Page<Order>(pageNumber,pageSize);
		
		//2.查询总条数，设置总条数
		int taotalRecord=od.getTotalRecord(uid);
		page.setTotalRecord(taotalRecord);
		//3.查询当前页书库，设置当期页数据
		List<Order> data=od.findMyOrdersByPage(page,uid);
		page.setData(data);
		
		return page;
	}
	/**
	 * 订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		
		return od.getById(oid);
	}
	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		od.update(order);
	}
	/**
	 * 后台按照状态查询订单
	 */
	@Override
	public Page<Order> findAllByState(String state,int pageNumber) throws Exception {
		//1.创建page
		int pageSize=Constant.ORDER_PAGESIZE;
		Page<Order> page=new Page<Order>(pageNumber,pageSize);
		
		//2.查询总条数，设置总条数
		int taotalRecord=od.getAdminTotalRecord(state);
		page.setTotalRecord(taotalRecord);
		//3.查询当前页书库，设置当期页数据
		List<Order> data=od.findAllByState(state,page);
		page.setData(data);		
		return page;
	}
	
	/**
	 * 删除订单
	 */
	@Override
	public int deleteOrder(String oid) throws Exception {
		return od.deleteOrder(oid);
	}

}
