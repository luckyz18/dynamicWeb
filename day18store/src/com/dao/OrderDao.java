package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.domain.Order;
import com.domain.OrderItem;
import com.domain.User;

public interface OrderDao {

	void add(Order order) throws Exception;

	void addItem(OrderItem oitem) throws Exception;

	List<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception;

	int findTotalCount(String uid) throws SQLException;

	Order getById(String oid)throws Exception;

	void update(Order order)throws Exception;

}
