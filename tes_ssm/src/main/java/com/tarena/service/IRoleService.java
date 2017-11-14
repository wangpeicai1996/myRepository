package com.tarena.service;

import java.util.List;

import com.tarena.entity.Role;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface IRoleService {
	//分页
	public Result findRolesByPage(Page page);
	//添加
	public Result addRole(Role role);
	//删除
	public Result deleteRole(String roleId);	
	//更新
	public Result updateRole(Role role);
	//查询所有角色的名字
	public Result findAllRoleName();
	//检查用户输入姓名
	public Result checkRoleName(String inputName);
}
