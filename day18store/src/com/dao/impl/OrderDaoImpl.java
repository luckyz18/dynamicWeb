package com.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.Product;
import com.domain.User;
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

	/**
	 * 分页查询订单
	 * @throws Exception 
	 */
	@Override
	public List<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception {
		//先查询订单集合
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ? order by ordertime desc limit ?,? ";
		List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), user.getUid(),(currPage-1)*pageSize,pageSize);
		
		//遍历订单 查询订单项
		sql = "select * from orderitem oi, product p where oi.pid = p.pid and oid = ? ";
		for (Order order : list) {
			List<Map<String,Object>> mList = qr.query(sql, new MapListHandler(),order.getOid());   //没有具体的实体类  没有用beanlisthandler
			
			//遍历map
			for ( Map<String, Object> map : mList) {
				
				//封装product
				Product p = new Product();
				BeanUtils.populate(p, map);
				
				//封装orderitem
				OrderItem oi = new OrderItem();
				BeanUtils.populate(oi,map);
				
				oi.setProduct(p);
				
				//将orderitem对象添加到对应的order对象的list中
				order.getItems().add(oi);
		
			}
		}
		return list;
	}

	/**
	 * 查询总条数
	 * @throws SQLException 
	 */
	@Override
	public int findTotalCount(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid = ? ";
		return ((Long)qr.query(sql, new ScalarHandler(), uid)).intValue();
		
	}

	/**
	 * 查询订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		// TODO 查询订单详情
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where oid = ? ";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
		
		sql = "select * from orderitem oi,product p where oi.pid = p.pid and oid = ? ";
		List<Map<String, Object>> mList = qr.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : mList) {
			//封装product
			Product p = new Product();
			BeanUtils.populate(p, map);
			
			//封装orderitem
			OrderItem oi = new OrderItem();
			BeanUtils.populate(oi, map);
			
			oi.setProduct(p);
			
			order.getItems().add(oi);
		}
		
		return order;
	}
	
	/**
	 * 更新订单信息
	 */
	@Override
	public void update(Order order) throws Exception {
		/**
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,
		 */
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state= ? , address = ? , name = ? , telephone = ? where oid = ?  ";
		qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	
	}

	/**
	 * 根据订单状态查询订单
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Override
	public List<Order> findAllByState(String state) throws NumberFormatException, Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where 1=1 ";
		if (state!=null ) {
			sql += " and state = ? order by ordertime desc ";
			return qr.query(sql, new BeanListHandler<>(Order.class), state);
		}
		sql += " order by ordertime desc ";
		return qr.query(sql, new BeanListHandler<>(Order.class));
		
		
	}
	
	
	
	
	

}
