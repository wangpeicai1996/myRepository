package com.tarena.service;

import com.tarena.vo.Result;

public interface ICourseService {
	//课程的分页
	public Result findCorseByPage(int currentPage,String courseName);
}
