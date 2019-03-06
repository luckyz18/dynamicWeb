package com.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.activation.DataSource;

import com.dao.AccountDao;
import com.dao.AccountDao4db;
import com.dao.AccountDao4tl;
import com.utils.DataSourceUtils;
import com.utils.JdbcUtils;

public class AccountService4db {

	public void account(String fromUser, String toUser, String money) throws Exception {
		AccountDao4db dao = new AccountDao4db();
		
		try {
			//开启事务
			DataSourceUtils.startTransction();
			dao.accountOut(fromUser,money); //转出
//			int i = 1/0;  //检验发生异常时 钱会不会飞
			dao.accountIn(toUser,money);  //转入
			//事务提交
			DataSourceUtils.commitAndClose();  //事务提交的时候   会从tl线程获取绑定的连接
			
		} catch (Exception e) {
			e.printStackTrace();
			//有异常 回滚
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
		
	}

}
