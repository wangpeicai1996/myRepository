package com.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pro.dao.CollectionDao;
import com.pro.domain.Collection;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.service.CollectionService;
import com.pro.utils.BeanFactory;
import com.pro.utils.UUIDUtils;

public class CollectionServiceImpl implements CollectionService{

	private CollectionDao cd=(CollectionDao) BeanFactory.getBean("CollectionDao");
	
	/**
	 * 添加收藏
	 */
	@Override
	public String addCollection(String pid, String uid) throws Exception {
		Collection collection=new Collection();
		collection.setCoid(UUIDUtils.getId());
		collection.setPid(pid);
		collection.setUid(uid);
		Collection backCollection=cd.findCollectionByPidAndUid(collection);
		if(backCollection==null) {
			int result=cd.addCollection(collection);
			if(result>0) {
				return "1";
			}else {
				return "2";
			}
		}else {
			return "3";
		}
		
	}

	@Override
	public List<Product> findMyCollectionsByPage(int pageNumber, int pageSize, String uid) throws Exception {
		//1.创建page
				Page<Collection> page=new Page<Collection>(pageNumber,pageSize);
				//2.查询总条数，设置总条数
				int taotalRecord=cd.getTotalRecord(uid);
				page.setTotalRecord(taotalRecord);
				//3.查询当前页书库，设置当期页数据
				List<Collection> data=cd.findMyCollectionsByPage(page, uid);
				page.setData(data);
				List<Product> productList=new ArrayList<Product>();
				for(int i=0;i<data.size();i++) {
					List<Product> pList=data.get(i).getProducts();
					for(int j=0;j<pList.size();j++) {
						Product p=pList.get(j);
						productList.add(p);
					}
				}
				return productList;
	}

	/**
	 * 删除收藏商品
	 * @throws Exception 
	 */
	@Override
	public int deleteCollection(String pid, String uid) throws Exception {
		return cd.deleteCollection(pid,uid);
	}

}
