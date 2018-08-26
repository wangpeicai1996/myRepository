package com.pro.dao;

import java.util.List;

import com.pro.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category c)throws Exception;

	void update(Category c)throws Exception;

	void delete(Category c)throws Exception;

}
