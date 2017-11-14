package com.tarena.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.CourseMapper;
import com.tarena.service.ICourseService;
import com.tarena.util.PageUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {
	@Resource(name="courseMapper")
	private CourseMapper courseMapper;
	@Resource(name="pageUtil")
	private PageUtil pageUtil;
	
	@Override
	public Result findCorseByPage(int currentPage, String courseName) {
		Result result=new Result();
		Page page=new Page();
		String courseKeyword=("undefined".equals(courseName))?"%%":"%"+courseName+"%";
		page.setCourseKeyword(courseKeyword);
		page.setCurrentPage(currentPage);
		int pageSize=this.pageUtil.getPageSize();
		page.setPageSize(pageSize);
		int totalCount=this.courseMapper.getCount(page.getCourseKeyword());
		page.setTotalCount(totalCount);
		if(page.getCurrentPage()==1) {
			page.setPreviousPage(1);
		}else {
			page.setPreviousPage(page.getCurrentPage()-1);
		}
		if(page.getCurrentPage()==page.getTotalPage()) {
			page.setNextPage(page.getTotalPage());
		}else {
			page.setNextPage(page.getCurrentPage()+1);
		}
		page.setData(this.courseMapper.pageData(page));
		page.setaNum(this.pageUtil.getFenYe_a_Num(currentPage, page.getPageSize(), totalCount, page.getTotalPage()));
		
		if(page.getData()!=null) {
			result.setStatus(0);
			result.setData(page);
		}else {
			result.setStatus(1);
			result.setMessage("没有课程信息");
		}
		return result;
	}

}
