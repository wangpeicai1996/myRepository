package com.tarena.dao;

import java.util.List;

public interface VideoMapper {
	//根据用户id查询所有的视频id
	public List<String> findVideoIdsByUserId(String userId);
	//根据用户id删除视频
	public int deleteVideoByUserId(String userId);
	//根据视频id删除历史缓存表
	public int deleteHistoryCacheByVideoId(String videoId);
}
