package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProvinceDao;
import com.domain.Province;

public class ProvinceService {

	/**
	 * 查找所有省
	 * @return list
	 * @throws SQLException 
	 */
	public List<Province> findAll() throws SQLException {
		return new ProvinceDao().findAll();
		
	}

}
