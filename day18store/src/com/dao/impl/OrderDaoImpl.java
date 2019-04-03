package com.dao.impl;

import org.apache.commons.dbutils.QueryRunner;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 *	 向order表添加1条数据
	 */
	@Override
	public void add(Order order) throws Exception {
		
			QueryRunner qr = new QueryRunner();
			String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
			qr.update(DataSourceUtils.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),
					order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	/**
	 *	 向orderitem表中添加 n 条数据
	 * @throws Exception 
	 */
	@Override
	public void addItem(OrderItem oitem) throws Exception {
		QueryRunner qr = new QueryRunner();  //手动事务
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql, oitem.getItemid(),oitem.getCount(),oitem.getSubtotal(),
				oitem.getProduct().getPid(),oitem.getOrder().getOid());
	}

}
