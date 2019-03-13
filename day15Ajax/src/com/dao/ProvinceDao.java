package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.domain.Province;
import com.utils.DataSourceUtils;

public class ProvinceDao {

	/**
	 * 查找所有省
	 * @return list
	 * @throws SQLException 
	 */
	public List<Province> findAll() throws SQLException {
		QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from province";
		return qr.query(sql,new BeanListHandler<>(Province.class));
		
	}

}
