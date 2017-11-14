package com.tarena.dao;

public interface ParticipationMapper {
	//删除指定用户参与的活动
	public int deleteParticipationByUserId(String userId);
	public int deleteParticipationByActivityId(String activityId);
}
