package com.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.domain.Dict;

public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	/**
	 * 获取所有客户级别
	 */
	public List<Dict> findByCode(String dict_type_code) {
		List<Dict> list = (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ? ", dict_type_code);
		return list;
	}

}
