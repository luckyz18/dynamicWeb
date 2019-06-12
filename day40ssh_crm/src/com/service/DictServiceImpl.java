package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.DictDao;
import com.domain.Dict;

@Transactional
public class DictServiceImpl implements DictService {
	private DictDao dictDao;
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	/**
	 * 获取客户所有客户级别
	 */
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}
	

}
