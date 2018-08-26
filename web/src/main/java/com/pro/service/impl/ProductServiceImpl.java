package com.pro.service.impl;

import java.io.File;
import java.util.List;

import com.pro.dao.ProductDao;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.service.ProductService;
import com.pro.utils.BeanFactory;

public class ProductServiceImpl implements ProductService{
	private ProductDao pd = (ProductDao)BeanFactory.getBean("ProductDao");
	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		return pd.findHot();
	}

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		return pd.findNew();


	}

	/**
	 * 单个商品详情
	 */
	@Override
	public Product getById(String pid) throws Exception {
		return pd.getById(pid);
	}

	/**
	 * 分页展示商品
	 */
	@Override
	public Page<Product> findByPage(int pageNumber, int pageSize, String cid,String keyword) throws Exception {
		//1.创建page
		Page<Product> pb=new Page<Product>(pageNumber,pageSize);
		//2.设置当前页数据
		List<Product> data=pd.findByPage(pb,cid,keyword);
		pb.setKeyword(keyword);
		pb.setData(data);
		//3.设置总记录数
		int totalRecord=pd.getTotalRecord(cid,keyword);
		pb.setTotalRecord(totalRecord);
		return pb;
	}

	/**
	 * 后台展示已上架商品
	 */
	@Override
	public List<Product> findAll(String state) throws Exception {
		return pd.findAll(state);
	}

	/**
	 * 保存商品
	 */
	@Override
	public void save(Product p) throws Exception {
		pd.save(p);
	}

	/**
	 * 编辑商品
	 */
	@Override
	public void update(Product p) throws Exception {
		pd.update(p);
	}

	/**
	 * 删除商品
	 */
	@Override
	public void delete(String pid,String pimage) throws Exception {
		int rowAffect=pd.delete(pid);
		if(rowAffect==1) {
			File file=new File(pimage);
			if(file.exists()) {
			file.delete();
			}
		}
	}
	
	
}
