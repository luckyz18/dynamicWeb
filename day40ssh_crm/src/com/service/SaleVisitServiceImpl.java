package com.service;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SaleVisitDao;
import com.domain.PageBean;
import com.domain.SaleVisit;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService{

	@Resource(name = "saleVisitDao")
	private SaleVisitDao saleVisitDao;


	public PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return saleVisitDao.findByPage(pageCode, pageSize, criteria);
	}


	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
}
