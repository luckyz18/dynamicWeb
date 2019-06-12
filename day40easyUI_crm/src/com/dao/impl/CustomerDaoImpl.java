package com.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dao.CustomerDao;
import com.domain.Customer;

@SuppressWarnings("all")
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	
	public Integer findTotalCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size()>0) {
			return  list.get(0).intValue();
		}
		return null;
	}

	
	public List<Customer> findByPage(DetachedCriteria criteria, int pageCode, Integer pageSize) {
		criteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
	}

	
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

}
