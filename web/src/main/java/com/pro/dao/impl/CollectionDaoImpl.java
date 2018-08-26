package com.pro.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pro.dao.CollectionDao;
import com.pro.domain.Collection;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.utils.DataSourceUtils;

public class CollectionDaoImpl implements CollectionDao {

	/**
	 * 添加收藏
	 */
	@Override
	public int addCollection(Collection collection) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into collections (coid,pid,uid) values(?,?,?)";
		int result=qr.update(sql, collection.getCoid(),collection.getPid(),collection.getUid());
		return result;
	}

	/**
	 * 通过pid和uid查找收藏用于判断用户是否重复收藏一个商品
	 */
	@Override
	public Collection findCollectionByPidAndUid(Collection collection) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from collections where pid=? and uid=?";
		Collection backCollection=qr.query(sql, new BeanHandler<>(Collection.class),
											collection.getPid(),collection.getUid());
		return backCollection;
	}

	/**
	 * 查询用户收藏，封装成list返回
	 */
	@Override
	public List<Collection> findMyCollectionsByPage(Page<Collection> page, String uid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		//1.查询所有订单基本信息
		String sql="select * from collections where uid=?";
		List<Collection> list=qr.query(sql, new BeanListHandler<>(Collection.class),uid);
		for(Collection col:list) {
			sql="select * from product where pid=?";
			//每一个list就是一个商品
			List<Product> productList=qr.query(sql,new BeanListHandler<>(Product.class),col.getPid());
				//将list放进collection对象
				col.setProducts(productList);
		}
		System.out.println("list="+list.toString());
		return list;
	}

	/**
	 * 获取用户收藏记录总数
	 */
	@Override
	public int getTotalRecord(String uid) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from collections where uid=?";
		return ((Long)qr.query(sql, new ScalarHandler(),uid)).intValue();
	}

	
	/**
	 * 删除收藏商品
	 */
	@Override
	public int deleteCollection(String pid, String uid) throws Exception {
		try {
			QueryRunner qr=new 	QueryRunner(DataSourceUtils.getDataSource());
			DataSourceUtils.startTransaction();
			String sql="delete from collections where pid=? and uid=?";
			int result = qr.update(sql,pid,uid);
			DataSourceUtils.commitAndClose();
			return result;
		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
		}
		
		return 0;
	}
}
