package com.domain;

import java.util.List;

public class PageBean<T> {
	private List<T> list;
	private int currentPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int gettotalPage() {
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}
	
	
	public PageBean(List<T> list, int currentPage, int pageSize, int totalCount) {
		super();
		this.list = list;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	
	
}
