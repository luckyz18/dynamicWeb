package com.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LinkmanDao;
import com.domain.Linkman;
import com.domain.PageBean;

@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	
	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}



	@Override
	public PageBean<Linkman> findByPage(Integer pageSize, Integer pageCode, DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}



	@Override
	public void add(Linkman linkman) {
		linkmanDao.save(linkman);
	}

}
