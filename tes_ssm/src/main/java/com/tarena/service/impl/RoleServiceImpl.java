package com.tarena.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.RoleMapper;
import com.tarena.entity.Role;
import com.tarena.service.IRoleService;
import com.tarena.util.PageUtil;
import com.tarena.util.UUIDUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Resource(name="roleMapper")
	private RoleMapper roleMapper;
    @Resource(name="pageUtil")
    private PageUtil pageUtil;
	
	public Result findRolesByPage(Page page) {
		page.setPageSize(pageUtil.getPageSize());
		int totalCount=this.roleMapper.getCount(page);
		page.setTotalCount(totalCount);
		
		int totalPage=page.getTotalPage();
		
		//处理前一页
		if(page.getCurrentPage()==1){
			page.setPreviousPage(1);
		}else{
			page.setPreviousPage(page.getCurrentPage()-1);
		}
		//处理后一页
		if(page.getCurrentPage()==totalPage){
			page.setNextPage(totalPage);
		}else{
			page.setNextPage(page.getCurrentPage()+1);
		}
		//获取超链接的个数
		page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
		
		page.setData(this.roleMapper.getRoles(page));
		Result result=new Result();
		if(page.getData()!=null){
			result.setStatus(0);
			result.setData(page);
		}else{
			result.setStatus(1);
			result.setMessage("没有角色信息");
		}
		
		return result;
	}

	public Result addRole(Role role) {
		role.setId(UUIDUtil.getUUID());
		this.roleMapper.addRole(role);
		Result result=new Result();
		result.setStatus(0);
		result.setMessage("添加角色成功");
		return result;
	}

	public Result deleteRole(String roleId) {
		this.roleMapper.deleteRole(roleId);
		Result result=new Result();
		result.setStatus(0);
		result.setMessage("删除角色成功");
		return result;	
	}

	public Result updateRole(Role role) {
		this.roleMapper.updateRole(role);
		Result result=new Result();
		result.setStatus(0);
		result.setMessage("修改角色成功");
		return result;	
	}

	public Result findAllRoleName() {
		Result result=new Result();
		List<Role> roles=this.roleMapper.findAllRoleName();
		if(roles!=null){
			result.setStatus(0);
			result.setData(roles);
		}else{
			result.setStatus(1);
			result.setMessage("无角色信息");
		}
		return result;
	}
	
	@Override
	public Result checkRoleName(String inputName) {
		List<Role> roles=this.roleMapper.checkRoleName(inputName);
		Result result=new Result();
		/*System.out.println("进入业务inputname="+inputName);
		System.out.println("roles="+roles.size()toString());
		System.out.println("roles.size="+roles.size());
		for(Role role:roles) {
			System.out.println("role="+role.toString());
		}
		
*/		
		System.out.println("roles="+roles);
		if(roles.size()!=0) {
			for(Role role : roles) {
				System.out.println("roleName="+role.getName());
				System.out.println(inputName.equals(role.getName()));
				if(role.getName().equals(inputName)) {
					result.setStatus(1);
					result.setMessage("角色名字已经存在");
					break;
				}else{
					result.setStatus(0);
					result.setMessage("角色名字可用");
				}
			}
		}else if(roles.size()==0){
			result.setStatus(0);
			result.setMessage("角色名字可用");
		}
System.out.println("result="+result.toString());
		return result;
	}

}
