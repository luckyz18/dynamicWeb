package com.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.domain.Linkman;
import com.utils.HibernateUtils;

public class QBCTest {
	/**
	 * 聚合函数查询
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Criteria criteria = session.createCriteria(Linkman.class);
		criteria.setProjection(Projections.count("lkm_id"));
		List<Number> list = criteria.list();
		Long count = list.get(0).longValue();
		System.out.println(count);
		
		//清除聚合函数的设定
		criteria.setProjection(null);
		List<Linkman> mans = criteria.list();
		for (Linkman linkman : mans) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * 条件查询
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Criteria criteria = session.createCriteria(Linkman.class);
		List<Linkman> mans = criteria.add(Restrictions.or(Restrictions.eq("lkm_gender", "男"), Restrictions.ge("lkm_id", 6L))).list();
		
		for (Linkman linkman : mans) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * 离线查询
	 * 不需要session
	 */
	@Test
	public void run3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		criteria.add(Restrictions.or(Restrictions.eq("lkm_gender", "男"), Restrictions.ge("lkm_id", 6L)));
		List list = criteria.getExecutableCriteria(session).list();
		for (Object object : list) {
			System.out.println(object);
		}
		tr.commit();
	}
	
	
}
