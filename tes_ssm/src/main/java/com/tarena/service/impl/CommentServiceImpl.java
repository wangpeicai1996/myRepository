package com.tarena.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.CommentMapper;
import com.tarena.entity.Comment;
import com.tarena.service.ICommentService;
import com.tarena.vo.Result;


@Service("commentService")
public class CommentServiceImpl implements ICommentService{
	@Resource(name="commentMapper")
	private CommentMapper commentMapper;
	
	@Override
	public Result findAllComment() {
		Result result=new Result();
		List<Comment> comments=this.commentMapper.findAllComment();
		if(comments!=null) {
			result.setStatus(0);
			result.setData(comments);
		}else {
			result.setStatus(1);
			result.setMessage("没有评论信息");
		}
		return result;
	}

}
