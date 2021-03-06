package com.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {
	
	private Long cust_id;
	private String cust_name;
	
	private Long cust_user_id;
	private Long cust_create_id;
	
	private String cust_linkman;
	private String cust_phone;
	private String cust_mobile;
	
	//在多方配置集合  数据字典表
	private Dict cust_source;
	private Dict cust_industry;
	private Dict cust_level;
	
	//文件上传
	private String filePath;
	
	//联系人
	@JSONField(serialize = false)
	private Set<Linkman> linkmans= new HashSet<Linkman>();
	
	public Dict getCust_source() {
		return cust_source;
	}
	public void setCust_source(Dict cust_source) {
		this.cust_source = cust_source;
	}
	public Dict getCust_industry() {
		return cust_industry;
	}
	public void setCust_industry(Dict cust_industry) {
		this.cust_industry = cust_industry;
	}
	public Dict getCust_level() {
		return cust_level;
	}
	public void setCust_level(Dict cust_level) {
		this.cust_level = cust_level;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public Long getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public Long getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Set<Linkman> getLinkmans() {
		return linkmans;
	}
	public void setLinkmans(Set<Linkman> linkmans) {
		this.linkmans = linkmans;
	}
	
	
}
