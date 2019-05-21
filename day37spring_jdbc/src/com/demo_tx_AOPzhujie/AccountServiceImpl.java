package com.demo_tx_AOPzhujie;

import org.springframework.transaction.annotation.Transactional;

/**
 * 在类上添加事务  所有的方法就都有事务了，也可以在某个方法上添加
 */
@Transactional
public class AccountServiceImpl implements AccountService{
	
	private AccountDao dao;
	public void setDao(AccountDaoImpl dao) {
		this.dao = dao;
	}


	@Override
	public void pay(String out, String in, Double money) {
		dao.out(out, money);
//		int i = 10/0;
		dao.in(in, money);
	}

}
