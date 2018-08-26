package com.pro.dao;

import java.util.List;

import com.pro.domain.Collection;
import com.pro.domain.Order;
import com.pro.domain.Page;

public interface CollectionDao {

	//添加收藏
	int addCollection(Collection collection) throws Exception;
	
	//查找用户收藏
	Collection findCollectionByPidAndUid(Collection collection) throws Exception;
	
	
	//查询用户所有收藏
	List<Collection> findMyCollectionsByPage(Page<Collection> page, String uid)throws Exception;
	
	int getTotalRecord(String uid) throws Exception;

	int deleteCollection(String pid, String uid)throws Exception;;
}
