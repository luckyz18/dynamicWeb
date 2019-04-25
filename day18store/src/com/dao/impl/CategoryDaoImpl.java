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

	/**
	 * 添加分类
	 * @throws SQLException 
	 */
	@Override
	public void add(Category category) throws SQLException {
		// TODO 添加分类
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?) ";
		qr.update(sql, category.getCid(),category.getCname());
		
	}

	/**
	 * 通过id 查找返回一个分类
	 * @throws SQLException 
	 */
	@Override
	public Category getById(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid= ? ";
		return qr.query(sql, new BeanHandler<>(Category.class), cid);
		
	}
	
	/**
	 * 更新分类信息
	 * @throws Exception 
	 */
	@Override
	public void update(Category category) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname = ? where cid = ? ";
		qr.update(sql, category.getCname(),category.getCid());
	}

	

	/**
	 * 根据cid删除该分类
	 * @throws Exception 
	 */
	@Override
	public void delete(String cid) throws Exception {
		QueryRunner qr = new QueryRunner();  
		String sql = "delete from category where cid= ? ";
		qr.update(DataSourceUtils.getConnection(), sql, cid);
	}

	
}
