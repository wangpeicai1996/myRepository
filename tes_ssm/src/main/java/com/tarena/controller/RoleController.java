package com.tarena.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.Role;
import com.tarena.service.IRoleService;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource(name="roleService")
	private IRoleService roleService;
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	@ResponseBody
	public Result findRolesByPage(Page page){
		page.setRoleKeyword("undefined".equals(page.getRoleKeyword()) ? "%%" : "%"+page.getRoleKeyword()+"%");
		Result result=null;
		
		result=this.roleService.findRolesByPage(page);
		
		return result;
	}
	@RequestMapping(value="/new",method=RequestMethod.POST)
	@ResponseBody
	public Result addRole(Role role){
		
		return this.roleService.addRole(role);
	}
	@RequestMapping(value="/delete/{roleId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result deleteRole(@PathVariable("roleId") String roleId){
		
		return this.roleService.deleteRole(roleId);
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result updateRole(Role role){
		
		return this.roleService.updateRole(role);
	}
	
	@RequestMapping(value="/check/username/{inputName}",method=RequestMethod.GET)
	@ResponseBody
	public Result checkRoleName(@PathVariable("inputName") String inputName){
		System.out.println("RoleController inputName="+inputName);
		
		return this.roleService.checkRoleName(inputName);
	}
	@RequestMapping(value="/allroles",method=RequestMethod.GET)
	@ResponseBody
	public Result findAllRoleName(){
		
		return this.roleService.findAllRoleName();
	}

}
