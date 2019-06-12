package com.service;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.PageBean;
import com.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	void save(SaleVisit saleVisit);

}
