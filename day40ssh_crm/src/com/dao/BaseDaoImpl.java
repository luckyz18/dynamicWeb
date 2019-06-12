package com.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.domain.Customer;
import com.domain.PageBean;

@SuppressWarnings("all")
public class BaseDaoImpl<T>  extends HibernateDaoSupport implements BaseDao<T> {
	private Class clazz;

	//构造方法
	public BaseDaoImpl() {
		Class c = this.getClass();   //this表示子类   c就是  customerDaoImpl的class对象
		Type type = c.getGenericSuperclass();  //获取的是 BaseDaoImpl<Customer>
		if (type instanceof  ParameterizedType) {
			 ParameterizedType pType = (ParameterizedType) type;
			 
			 Type[] types = pType.getActualTypeArguments();  //获取Customer
			 this.clazz = (Class) types[0];
		}
	}

	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<T> page = new PageBean<T>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list!=null && list.size()>0 ) {
			int totalCount = list.get(0).intValue();
			page.setTotalCount(totalCount);
		}
		//清除 格式
		criteria.setProjection(null);
		
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
		page.setBeanList(beanList);
		return page;
	}

}
