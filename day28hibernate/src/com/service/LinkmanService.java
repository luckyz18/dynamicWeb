package com.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.dao.LinkmanDao;
import com.domain.Customer;
import com.domain.Linkman;
import com.utils.HibernateUtils;

public class LinkmanService {

	/**
	 * 添加联系人
	 * @param linkman
	 * @param id
	 */
	public void add(Linkman linkman, Long id) {
		LinkmanDao dao = new LinkmanDao();
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			Customer customer = new CustomerService().getById(id);
			dao.save(linkman,customer);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
	}
	
	/**
	 * 联系人列表
	 * @param criteria  筛选
	 * @return
	 */
	public List<Linkman> list(DetachedCriteria criteria) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Linkman> list = null;
		try {
			LinkmanDao dao = new LinkmanDao();
			list = dao.getAll(criteria);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}	

}
