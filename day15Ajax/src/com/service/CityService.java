package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.CityDao;
import com.domain.City;

public class CityService {

	/**
	 * 	根据省 查找市
	 * @param pid
	 * @return list<City>
	 * @throws SQLException 
	 */
	public List<City> findCityByPid(String pid) throws SQLException {
		return new CityDao().findCityByPid(pid);
	}

}
