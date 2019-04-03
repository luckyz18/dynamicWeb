package com.dao;

import java.sql.SQLException;

import com.domain.Order;
import com.domain.OrderItem;

public interface OrderDao {

	void add(Order order) throws Exception;

	void addItem(OrderItem oitem) throws Exception;

}
