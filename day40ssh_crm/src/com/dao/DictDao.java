package com.dao;

import java.util.List;

import com.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
