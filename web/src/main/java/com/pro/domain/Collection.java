package com.pro.domain;

import java.util.List;
import java.util.Map;

public class Collection {

	
	private String coid;
	private String pid;
	private String uid;
	
	private List<Product> products; 
	
	public String getCoid() {
		return coid;
	}
	public void setCoid(String coid) {
		this.coid = coid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Collection [coid=" + coid + ", pid=" + pid + ", uid=" + uid + ", products=" + products + "]";
	}
	
	
	
}
