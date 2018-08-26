package com.pro.service;

import java.util.List;

import com.pro.domain.Product;

public interface CollectionService {
	//添加收藏
	String addCollection(String pid,String uid) throws Exception;
	
	//查询收藏
	List<Product> findMyCollectionsByPage(int pageNumber, int pageSize, String uid)throws Exception;

	//删除收藏商品
	int deleteCollection(String pid,String uid)throws Exception;
}
