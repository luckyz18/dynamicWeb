package com.service;

import java.util.List;

import com.domain.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
