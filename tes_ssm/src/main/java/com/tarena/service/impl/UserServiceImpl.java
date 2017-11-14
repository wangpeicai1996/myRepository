package com.tarena.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tarena.dao.ActivityMapper;
import com.tarena.dao.CommentMapper;
import com.tarena.dao.ParticipationMapper;
import com.tarena.dao.UserMapper;
import com.tarena.dao.VideoMapper;
import com.tarena.entity.User;
import com.tarena.entity.UserRole;
import com.tarena.service.IUserService;
import com.tarena.util.CommonValue;
import com.tarena.util.ExcelUtil;
import com.tarena.util.PageUtil;
import com.tarena.util.PrintWriterUtil;
import com.tarena.util.UUIDUtil;
import com.tarena.util.UploadUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	@Resource(name = "participationMapper")
	private ParticipationMapper participationMapper;
	@Resource(name = "activityMapper")
	private ActivityMapper activityMapper;
	@Resource(name = "commentMapper")
	private CommentMapper commentMapper;
	@Resource(name = "videoMapper")
	private VideoMapper videoMapper;

	@Resource(name = "pageUtil")
	private PageUtil pageUtil;

	public boolean login(User user) {
		boolean flag = false;
		String userId = this.userMapper.login(user);
		if (userId != null) {
			flag = true;
		}
		return flag;
	}

	public Result findUsersByPage(Page page) {
		Result result = new Result();
		if("%all%".equals(page.getRoleType())){
			//查询所有角色
			page.setPageSize(pageUtil.getPageSize());

			int totalCount = this.userMapper.getCount(page);
			page.setTotalCount(totalCount);

			int totalPage = page.getTotalPage();

			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			page.setData(this.userMapper.getUsers(page));

			if (page.getData() != null) {
				result.setStatus(0);
				result.setData(page);
			} else {
				result.setStatus(1);
				result.setMessage("没有用户信息");
			}
		}else{
			//查询指定角色
			page.setPageSize(pageUtil.getPageSize());

			int totalCount = this.userMapper.getCountByRole(page);
			page.setTotalCount(totalCount);

			int totalPage = page.getTotalPage();

			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			page.setData(this.userMapper.getUsersByRole(page));

			if (page.getData() != null) {
				result.setStatus(0);
				result.setData(page);
			} else {
				result.setStatus(1);
				result.setMessage("没有用户信息");
			}

		}
		
		
		
		return result;
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Result deleteUser(String userId) {
		Result result = new Result();
		// -------------------
		this.userMapper.deleteUserRole(userId);
		// ------------------
		// 删除跟活动相关的所有数据
		// 先删除用户参与的活动的参与表中的数据
		this.participationMapper.deleteParticipationByUserId(userId);
		// 根据用户id查询出所有的活动id
		List<String> activityIds = this.activityMapper.findActivityIds(userId);
		for (String activityId : activityIds) {
			this.participationMapper.deleteParticipationByActivityId(activityId);
		}
		// 活动参与表删除完毕后再删除指定用户id的所有活动
		this.activityMapper.deleteActivityByUserId(userId);
		// ------------------
		// 删除评论
		this.commentMapper.deleteCommentByUserId(userId);
		// 查询指定用户的id的所有视频id
		List<String> videoIds = this.videoMapper.findVideoIdsByUserId(userId);
		for (String videoId : videoIds) {
			this.commentMapper.deleteCommentByVideoId(videoId);
			this.videoMapper.deleteHistoryCacheByVideoId(videoId);
		}

		// 删除历史缓存表中的信息
		this.userMapper.deleteHistoryCacheByUserId(userId);

		this.videoMapper.deleteVideoByUserId(userId);
		// -------------
		// 删除用户

		this.userMapper.deleteUser(userId);
		result.setStatus(0);
		result.setMessage("删除用户成功");

		return result;
	}

	public Result findUserById(String userId) {
		Result result = new Result();

		User user = this.userMapper.findUserById(userId);
		if (user != null) {
			result.setStatus(0);
			result.setData(user);
		}

		return result;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Result addUser(User user, String roleId, MultipartFile addPicture, HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		// 处理上传文件
		String originalFileName = null;
		String userId = UUIDUtil.getUUID();
		String realPath = request.getServletContext().getRealPath("/head");

		if (addPicture == null || addPicture.isEmpty()) {
			user.setHead("default.png");
			// PrintWriterUtil.printMessageToClient(response, "请选择文件后上传");
			// return null;
		} else {
			String contentType = addPicture.getContentType();
			long size = addPicture.getSize();
			if (!CommonValue.contentTypes.contains(contentType)) {
				PrintWriterUtil.printMessageToClient(response, "图片类型不匹配");
				return null;
			} else if (size > 4194304) {
				PrintWriterUtil.printMessageToClient(response, "图片文件太大");
				return null;
			} else {
				// 开始上传文件
				boolean flag = UploadUtil.uploadImage(addPicture, userId, true, 64, realPath);
				if (!flag) {
					PrintWriterUtil.printMessageToClient(response, "文件上传失败!请重新上传");
					return null;
				}
			}
		}
		String imageFileName = null;
		try {
			// 调用添加用户数据库操作
			originalFileName = addPicture.getOriginalFilename();
			String originalExtendName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			imageFileName = userId + "." + originalExtendName;
			user.setHead(imageFileName);
			user.setId(userId);

			this.userMapper.addUser(user);
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			this.userMapper.addUserRole(ur);
			PrintWriterUtil.printMessageToClient(response, "用户添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File file = new File(realPath + File.pathSeparator + imageFileName);
			if (file.exists()) {
				file.delete();
			}
			return null;
		}
		return result;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Result updateUser(User user, String[] roleIds, MultipartFile updatePicture, HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		// 处理上传文件
		String originalFileName = null;
		String userId = user.getId();
		String realPath = request.getServletContext().getRealPath("/head");

		if (updatePicture == null || updatePicture.isEmpty()) {
			
			// PrintWriterUtil.printMessageToClient(response, "请选择文件后上传");
			// return null;
		} else {
			String contentType = updatePicture.getContentType();
			long size = updatePicture.getSize();
			if (!CommonValue.contentTypes.contains(contentType)) {
				PrintWriterUtil.printMessageToClient(response, "图片类型不匹配");
				return null;
			} else if (size > 4194304) {
				PrintWriterUtil.printMessageToClient(response, "图片文件太大");
				return null;
			} else {
				// 开始上传文件
				boolean flag = UploadUtil.uploadImage(updatePicture, userId, true, 64, realPath);
				if (!flag) {
					PrintWriterUtil.printMessageToClient(response, "文件上传失败!请重新上传");
					return null;
				}
			}
		}
		//做数据存储
		String imageFileName = null;
		try {
			// 调用添加用户数据库操作
			originalFileName = updatePicture.getOriginalFilename();
			String originalExtendName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			imageFileName = userId + "." + originalExtendName;
			user.setHead(imageFileName);
			
			//更新一次用户
			this.userMapper.updateUser(user);
			//删除用户和角色关联表中的指定用户id的所有数据
			this.userMapper.deleteUserRole(userId);
			//循环添加用户和角色关联表指定用户的所有角色信息
			for(String roleId : roleIds){
				UserRole ur=new UserRole();
				ur.setUserId(userId);
				ur.setRoleId(roleId);
				this.userMapper.addUserRole(ur);
			}
			
			PrintWriterUtil.printMessageToClient(response, "用户修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File file = new File(realPath + File.pathSeparator + imageFileName);
			if (file.exists()) {
				file.delete();
			}
			return null;
		}

		return result;
	}
	//导出数据到excel中
	public byte[] exportUser() {
		byte[] data=null;
		//查询要导出到excel中的数据
		List<User> allUsers=this.userMapper.findAllUsers();
		if(allUsers!=null && allUsers.size()>0){
			data=ExcelUtil.write2Excel(allUsers);
		}
		return data;
	}
}
