package com.service;

import java.util.List;

import com.domain.Order;
import com.domain.Pagebean;
import com.domain.User;

public interface OrderService {

	void add(Order order) throws Exception;

	Pagebean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order)throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
