package com.dao;

import java.util.List;

import com.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

}
