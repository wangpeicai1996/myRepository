package com.tarena.dao;

import java.util.List;

public interface ActivityMapper {
	//根据用户id查询出所有的活动id
	public List<String> findActivityIds(String userId);
	
	//删除指定用户创建的活动
	public int deleteActivityByUserId(String userId);
}
