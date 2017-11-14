package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Comment;

public interface CommentMapper {
	//根据用户id删除评论
	public int deleteCommentByUserId(String userId);
	
	//根据视频的id删除评论
	public int deleteCommentByVideoId(String videoId);
	
	//查询所有评论
	public List<Comment> findAllComment();
}
