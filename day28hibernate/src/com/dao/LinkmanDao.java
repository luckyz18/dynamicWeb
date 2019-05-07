package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.domain.Customer;
import com.domain.Linkman;
import com.utils.HibernateUtils;

public class LinkmanDao {

	/**
	 * 保存联系人
	 * @param linkman
	 * @param customer
	 */
	public void save(Linkman linkman, Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		linkman.setCustomer(customer);
		session.save(linkman);
	}

	/**
	 * 联系人列表
	 * @param criteria2
	 * @return
	 */
	public List<Linkman> getAll(DetachedCriteria criteria) {
		Session session = HibernateUtils.getCurrentSession();
		return criteria.getExecutableCriteria(session).list();
	}
}
