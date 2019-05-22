package com.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	/*private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}*/


	@Override
	public void save(Customer customer) {
		/*把数据持久到数据库*/
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public Customer getByID(long id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	public List<Customer> findAllByQBC() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
		
	}

	@Override
	public Customer load(long id) {
		return this.getHibernateTemplate().load(Customer.class, id);
	}

}
