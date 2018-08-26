package com.pro.dao;

import java.util.List;

import com.pro.domain.Page;
import com.pro.domain.Product;

public interface ProductDao {
	List<Product> findHot()throws Exception;
	List<Product> findNew()throws Exception;
	Product getById(String pid)throws Exception;
	List<Product> findByPage(Page<Product> pb, String cid,String keyword)throws Exception;
	int getTotalRecord(String cid,String keyword)throws Exception;
	List<Product> findAll(String state)throws Exception;
	void save(Product p)throws Exception;
	void update(Product p)throws Exception;
	int delete(String pid)throws Exception;

}
