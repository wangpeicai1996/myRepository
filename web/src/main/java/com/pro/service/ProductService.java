package com.pro.service;

import java.util.List;

import com.pro.domain.Page;
import com.pro.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew()throws Exception;

	Product getById(String pid)throws Exception;

	Page<Product> findByPage(int pageNumber, int pageSize, String cid,String keyword)throws Exception;

	List<Product> findAll(String state)throws Exception;

	void save(Product p)throws Exception;

	void update(Product p)throws Exception;

	void delete(String pid,String pimage)throws Exception;
}
