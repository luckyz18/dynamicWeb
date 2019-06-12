package com.service;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.Linkman;
import com.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer pageSize, Integer pageCode, DetachedCriteria criteria);

	void add(Linkman linkman);

}
