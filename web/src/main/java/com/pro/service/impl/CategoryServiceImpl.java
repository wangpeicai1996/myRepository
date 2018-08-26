package com.pro.service.impl;

import java.util.List;

import com.pro.dao.CategoryDao;
import com.pro.dao.impl.CategoryDaoImpl;
import com.pro.domain.Category;
import com.pro.service.CategoryService;
import com.pro.utils.BeanFactory;
import com.pro.utils.JsonUtils;

public class CategoryServiceImpl implements CategoryService{
	private CategoryDao cd=(CategoryDao)BeanFactory.getBean("CategoryDao");
	/**
	 * 前台查询所有分类
	 */
	@Override
	public String findAll() throws Exception {
		//1.调用dao，查询所有分类
		List<Category> list=cd.findAll();
		if(list!=null&&list.size()>0) {
			return JsonUtils.Object2Json(list);
		}
		return null;
	}
	/**
	 * 后台查询所有分类
	 */
	@Override
	public List<Category> findList() throws Exception {
		return cd.findAll();
	}
	/**
	 * 添加分类
	 */
	@Override
	public void save(Category c) throws Exception {
		cd.save(c);	
	}
	/**
	 * 编辑分类
	 */
	@Override
	public void update(Category c) throws Exception {
		cd.update(c);
	}
	
	/**
	 * 删除分类
	 */
	@Override
	public void delete(Category c) throws Exception {
		cd.delete(c);
	}

}
