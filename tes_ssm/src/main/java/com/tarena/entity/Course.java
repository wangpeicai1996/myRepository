package com.tarena.entity;

public class Course {
private String id;
private String name;
private String picture;
private int order;
private String desc;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public int getOrder() {
	return order;
}
public void setOrder(int order) {
	this.order = order;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
@Override
public String toString() {
	return "Course [id=" + id + ", name=" + name + ", picture=" + picture + ", order=" + order + ", desc=" + desc + "]";
}

}
