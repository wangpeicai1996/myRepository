package com.pcwang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pcwang.domain.User_Info;

/**
 * user_info实体对象dao层接口
 * @author Administrator
 *
 */
@Repository
public interface User_Info_Mapper {

	public User_Info getUser(@Param("username")String username,@Param("password")String password);
	
}
