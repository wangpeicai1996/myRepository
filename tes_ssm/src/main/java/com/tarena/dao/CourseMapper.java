package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Course;
import com.tarena.vo.Page;

public interface CourseMapper {
	public int getCount(String courseName);
	public List<Course> pageData(Page page);
}
