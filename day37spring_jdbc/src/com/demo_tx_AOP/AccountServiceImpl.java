package com.demo_tx_AOP;

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
