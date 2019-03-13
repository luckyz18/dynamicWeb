package com.service;

import java.util.List;

import com.dao.SearchKwDao;

public class SearchKwService {

	public List<Object> findKw4Ajax(String kw) throws Exception {
		return new SearchKwDao().findKw4Ajax(kw);
	}

}
