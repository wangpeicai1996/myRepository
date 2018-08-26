package com.pro.service;

import java.util.List;

import com.pro.domain.Category;

public interface CategoryService {

	String findAll() throws Exception;
	
	List<Category> findList()throws Exception;

	void save(Category c)throws Exception;

	void update(Category c)throws Exception;

	void delete(Category c)throws Exception;

}
