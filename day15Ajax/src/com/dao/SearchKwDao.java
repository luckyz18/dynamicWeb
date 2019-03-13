package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.utils.DataSourceUtils;

public class SearchKwDao {

	public List<Object> findKw4Ajax(String kw) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from keyword where kw like ? limit 5";
		return qr.query(sql, new ColumnListHandler("kw"), "%"+kw+"%");
		
	}

}
