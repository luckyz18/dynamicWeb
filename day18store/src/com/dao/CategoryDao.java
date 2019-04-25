package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;


	void add(Category category)throws SQLException;


	Category getById(String cid) throws SQLException;


	void update(Category category) throws Exception;

	void delete(String cid) throws Exception;

}
