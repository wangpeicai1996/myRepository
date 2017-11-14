package com.tarena.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tarena.service.ICourseService;
import com.tarena.vo.Result;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Resource(name="courseService")
	private ICourseService courseService;

	@RequestMapping(value="/page/{currentPage}/{courseName}",method=RequestMethod.GET)
	@ResponseBody
	public Result findCourseByPage(@PathVariable("currentPage") int currentPage,
								@PathVariable("courseName") String courseName) {
		Result result=new Result();
		result=this.courseService.findCorseByPage(currentPage, courseName);
		System.out.println("result="+result.toString());
		return result;
		
	}
}
