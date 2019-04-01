package com.service;

import java.util.List;

import com.domain.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

}
