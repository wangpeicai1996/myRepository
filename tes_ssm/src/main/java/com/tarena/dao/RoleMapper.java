package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Role;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface RoleMapper {
	// 分页
	public int getCount(Page page);
	public List<Role> getRoles(Page page);

	// 添加
	public int addRole(Role role);

	// 删除
	public int deleteRole(String roleId);

	// 更新
	public int updateRole(Role role);

	// 查询所有角色的名字
	public List<Role> findAllRoleName();
	
	//查询指定角色名字在数据库中是否存在(个数)
	public int findRoleName(String roleName);
	//检查用户所输入的角色名
	public List<Role> checkRoleName(String inputName); 
}
