package com.pro.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pro.dao.OrderDao;
import com.pro.domain.Order;
import com.pro.domain.OrderItem;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao{

	/**
	 * 保存订单
	 */
	@Override
	public void save(Order order) throws Exception {
			QueryRunner qr=new QueryRunner();
			String sql="insert into orders values(?,?,?,?,?,?,?,?)";
			qr.update(DataSourceUtils.getConnection(),sql,order.getOid(),order.getOrdertime(),
					order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),
					order.getUser().getUid());
	}
	/**
	 * 保存订单项
	 */
	@Override
	public void saveItem(OrderItem oi) throws Exception {
		QueryRunner qr=new QueryRunner();
		String sql="insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql,oi.getItemid(),oi.getCount(),oi.getSubtotal(),
				oi.getProduct().getPid(),oi.getOrder().getOid());
	}
	/**
	 * 获取订单总记录数
	 */
	@Override
	public int getTotalRecord(String uid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from orders where uid=?";
		return ((Long)qr.query(sql, new ScalarHandler(),uid)).intValue();
	}
	/**
	 * 获取我的订单当前页数据
	 */
	@Override
	public List<Order> findMyOrdersByPage(Page<Order> page, String uid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		//1.查询所有订单基本信息
		String sql="select * from orders where uid=? order by ordertime desc limit ?,?";
		List<Order> list=qr.query(sql, new BeanListHandler<>(Order.class),uid,page.getStartIndex(),page.getPageSize());
		//2.遍历订单集合，获取每一个订单，查询每一个订单中的订单项
		for (Order order : list) {
			sql="select * from orderitem oi,product p where oi.pid=p.pid and oi.oid=?";
			List<Map<String,Object>> maplist=qr.query(sql, new MapListHandler(),order.getOid());;
			//遍历maplist，获取每一个订单项的详情，封装成orderitem，将其添加到当前订单的订单项列表中
			for(Map<String,Object> map:maplist) {
				//封装成orderitem
				OrderItem oi=new OrderItem();
				BeanUtils.populate(oi,map);
				//封装product
				Product product=new Product();
				BeanUtils.populate(product, map);
				oi.setProduct(product);
				//将orderitem放入order的订单列表
				order.getItems().add(oi);
			}
		}
		return list;
	}
	/**
	 * 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		//1.查询订单基本信息
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders where oid=?";
		Order order= qr.query(sql, new BeanHandler<>(Order.class),oid);
		//2.查询订单项
		sql="select * from orderitem oi,product p where oi.pid=p.pid and oi.oid=?";
		//所有订单详情
		List<Map<String,Object>> maplist=qr.query(sql, new MapListHandler(),order.getOid());;
		//遍历maplist，获取每一个订单项的详情，封装成orderitem，将其添加到当前订单的订单项列表中
		for(Map<String,Object> map:maplist) {
			//封装成orderitem
			OrderItem oi=new OrderItem();
			BeanUtils.populate(oi,map);
			//封装product
			Product product=new Product();
			BeanUtils.populate(product, map);
			oi.setProduct(product);
			//将orderitem放入order的订单列表
			order.getItems().add(oi);
		}
		return order;
	}
	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql,order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
		
	}
	
	
	/**
	 * 后台获取订单总记录数
	 */
	@Override
	public int getAdminTotalRecord(String state) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from orders ";
		//判断state是否为空
		if(state==null||state.trim().length()==0) {
			sql+="order by ordertime desc";
			return ((Long)qr.query(sql, new ScalarHandler())).intValue();
		}
		sql+="where state=? order by ordertime desc";
		return  ((Long)qr.query(sql, new ScalarHandler(),state)).intValue();
	}
	/**
	 * 后台按照状态查询订单
	 */
	@Override
	public List<Order> findAllByState(String state,Page page) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders ";
		//判断state是否为空
		if(state==null||state.trim().length()==0) {
			sql+="order by ordertime desc limit ?,?";
			return qr.query(sql, new BeanListHandler<>(Order.class),page.getStartIndex(),page.getPageSize());
		}
		sql+="where state=? order by ordertime desc limit ?,?";
		return  qr.query(sql, new BeanListHandler<>(Order.class),state,page.getStartIndex(),page.getPageSize());
	}
	
	/**
	 * 删除订单
	 */
	@Override
	public int deleteOrder(String oid) throws Exception {
		try {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			DataSourceUtils.startTransaction();//开启事务
			//通过oid先删除orderitem表
			String sql1="delete from orderitem where oid=?";
			int num1=qr.update(sql1, oid);
			//通过oid删除order表
			if(num1>0) {
				String sql2="delete from orders where oid=?";
				int num2=qr.update(sql2,oid);
				DataSourceUtils.commitAndClose();//事务提交
				return num2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();//事务回滚
			return 0;
			
		}
		return 0;
	}

}
