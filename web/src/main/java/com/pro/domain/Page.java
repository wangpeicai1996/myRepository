package com.pro.domain;

import java.util.List;

/**
 * 分页实体
 * @author Administrator
 * @param <T>
 *
 */
public class Page<T> {
	private int pageNumber;//当前页面
	private String keyword="";//模糊查询关键字
	private int pageSize;//每页显示的条数
	private int totalRecord;//总条数 查询出来
	private int totalPage;//总页数	计算
	private List<T> data;//当前页的数据 查询 
	private int startIndex;//limit后面的m (pageNumber-1)*pageSize
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		return (this.totalRecord%this.pageSize==0)?(this.totalRecord/this.pageSize):(this.totalRecord/this.pageSize+1);
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getStartIndex() {
		if(this.pageNumber-1>=0) {
			return (this.pageNumber-1)*this.pageSize;
		}
		return 0;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public Page() { }
	
	
	public Page(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", keyword=" + keyword + ", pageSize=" + pageSize + ", totalRecord="
				+ totalRecord + ", totalPage=" + totalPage + ", data=" + data + ", startIndex=" + startIndex + "]";
	}
	
}
