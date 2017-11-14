package com.tarena.entity;

import java.sql.Timestamp;

public class Comment {
	private String id;
	private String content;
	private Timestamp timestamp;
	private	Video video;
	private User user;
	private Course course;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", timestamp=" + timestamp + ", video=" + video
				+ ", user=" + user + ", course=" + course + "]";
	}
	
	
}
