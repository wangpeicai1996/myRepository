package com.tarena.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.service.ICommentService;
import com.tarena.vo.Result;

@Controller
@RequestMapping("/find")
public class CommentController {
	@Resource(name="commentService")
	private ICommentService commentService;
	
	@RequestMapping("/comment")
	@ResponseBody
	public Result findAllComment() {
		Result result=new Result();
		result=this.commentService.findAllComment();
		System.out.println("result="+result.getData().toString());
		return result;
	}
}
