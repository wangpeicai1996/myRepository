package com.tarena.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tarena.entity.User;
import com.tarena.service.IUserService;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userService")
	private IUserService userService;
	@RequestMapping(value="/login/{name}/{pwd}",method=RequestMethod.GET)
	@ResponseBody
	public Result login(@PathVariable("name") String loginName,
			            @PathVariable("pwd") String password,
			            HttpSession session){
		Result result=new Result();
		User user=new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		
		if(this.userService.login(user)){
			result.setStatus(0);
			session.setAttribute("loginName", loginName);
		}else{
			result.setStatus(1);
			result.setMessage("用户名和密码错误!");
		}
		return result;
	}
	
	
	@RequestMapping(value="/page/{currentPage}/{userKeyword}/{roleType}",method=RequestMethod.GET)
	@ResponseBody
	public Result findUserByPage(@PathVariable("currentPage") int currentPage,
			            @PathVariable("userKeyword") String userKeyword,
			            @PathVariable("roleType") String roleType){
		Result result=null;
		Page page=new Page();
		page.setCurrentPage(currentPage);
		page.setUserKeyword("undefined".equals(userKeyword) ? "%%" :"%"+userKeyword+"%");
		page.setRoleType(roleType);
		
		result=this.userService.findUsersByPage(page);
		
		return result;
	}
	@RequestMapping(value="/delete/{userId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result deleteUser(@PathVariable("userId") String userId){
		Result result=null;
		result=this.userService.deleteUser(userId);
		
		return result;
	}
	@RequestMapping(value="/detail/{userId}",method=RequestMethod.GET)
	@ResponseBody
	public Result findUserById(@PathVariable("userId") String userId){
		Result result=null;
		
		result=this.userService.findUserById(userId);
		
		return result;
	}
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public Result addUser(User user,
			              String roleId,
			              @RequestParam("addPicture") MultipartFile addPicture,
			              HttpServletRequest request,
			              HttpServletResponse response){
		Result result=null;
		
		result=this.userService.addUser(user, roleId, addPicture,request,response);
		
		return result;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Result updateUser(User user,
			              @RequestParam("roleIds") String[] roleIds,
			              @RequestParam("updatePicture") MultipartFile updatePicture,
			              HttpServletRequest request,
			              HttpServletResponse response){
		Result result=null;
		
		result=this.userService.updateUser(user, roleIds, updatePicture, request, response);
		
		return result;
	}
	@RequestMapping(value="/exportuser",method=RequestMethod.GET)
	public void exportUser(HttpServletRequest request,
			                 HttpServletResponse response){
		
		
		try {
			byte[] data=this.userService.exportUser();
			//下载data数组为excel文件
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;fileName=alluser.xls");
			response.setContentLength(data.length);
			OutputStream os=response.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
