package com.service.impl;

import java.util.List;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.Pagebean;
import com.domain.User;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService{

	/**
	 * 	提交订单
	 * @throws Exception 
	 */
	@Override
	public void add(Order order) throws Exception{
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
		
			OrderDao dao = (OrderDao) BeanFactory.getbean("OrderDao");
			//2.向order表添加一个订单
			dao.add(order);
			
			//3.向orderItem表中添加  n 条数据
			for (OrderItem oitem: order.getItems()) {
				dao.addItem(oitem);
			}
			
			//4.提交事务
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
		
	}

	/**
	 * 分页查询我的订单
	 * @throws Exception 
	 */
	@Override
	public Pagebean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception {
		// TODO 分页查询我的订单
		OrderDao  dao = (OrderDao) BeanFactory.getbean("OrderDao");
		
		//查询当前页数据
		List<Order> list = dao.findAllByPage(currPage,pageSize,user);
		
		//查询总条数 totalCount
		int totalCount= dao.findTotalCount(user.getUid());
		
		return new Pagebean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao  dao = (OrderDao) BeanFactory.getbean("OrderDao");
		return dao.getById(oid);
		
	}

	/**
	 * 更新订单信息
	 */
	@Override
	public void update(Order order) throws Exception {
		OrderDao  dao = (OrderDao) BeanFactory.getbean("OrderDao");
		dao.update(order);
		
	}

	/**
	 * 根据订单状态查询订单
	 * @throws Exception 
	 */
	@Override
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao dao= (OrderDao) BeanFactory.getbean("OrderDao");
		return dao.findAllByState(state);
	}

}
