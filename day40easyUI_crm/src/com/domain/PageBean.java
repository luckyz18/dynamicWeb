package com.domain;

import java.util.List;

public class PageBean<T> {
	private Integer pageCode;  /*当前页*/
	private Integer totalCount;
	private Integer pageSize;
	private List<T> beanList;
	
	public Integer getTotalPage() {
		return (int) Math.ceil((totalCount*1.0)/pageSize);
	}
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	
}
