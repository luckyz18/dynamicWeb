package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.domain.User;
import com.utils.HibernateUtils;

public class UserDao {
	/**
	 * 根据用户名密码获取用户
	 * @param user
	 * @return
	 */
	public  User getByNameAndPwd(User user) {
		Session session = HibernateUtils.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user.getUsername()))
				.add(Restrictions.eq("password", user.getPassword()));
		List<User> list = criteria.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
