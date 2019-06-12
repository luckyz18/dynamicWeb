package com.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.PageBean;
import com.domain.SaleVisit;

public interface SaleVisitDao extends BaseDao<SaleVisit>{

	PageBean<SaleVisit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
		

}
