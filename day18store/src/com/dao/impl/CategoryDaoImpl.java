package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.CategoryDao;
import com.domain.Category;
import com.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	/**
	 * 查找所有分类
	 * @throws SQLException 
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		// TODO 查找所有分类
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category ";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}
	
}
