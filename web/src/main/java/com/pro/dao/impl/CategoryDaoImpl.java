package com.pro.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pro.dao.CategoryDao;
import com.pro.domain.Category;
import com.pro.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao{

	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	/**
	 * 添加分类
	 */
	@Override
	public void save(Category c) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into category values(?,?)";
		qr.update(sql,c.getCid(),c.getCname());
	}

	/**
	 * 编辑分类
	 */
	@Override
	public void update(Category c) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update category set cname=? where cid=?";
		qr.update(sql,c.getCname(),c.getCid());
	}

	/**
	 * 删除分类
	 */
	@Override
	public void delete(Category c) throws Exception {
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="delete from category where cid=?";
			qr.update(sql,c.getCid());
			//提交事务
			DataSourceUtils.commitAndClose();
		}catch(Exception e) {
			e.printStackTrace();
			//回滚事务
			DataSourceUtils.rollbackAndClose();
		}
		
	}

}
