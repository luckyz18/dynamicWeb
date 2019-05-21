package com.demo_tx;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements AccountService{
	
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	private AccountDao dao;
	public void setDao(AccountDaoImpl dao) {
		this.dao = dao;
	}


	@Override
	public void pay(String out, String in, Double money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				dao.out(out, money);
//				int i = 10/0;
				dao.in(in, money);
			}
		});
	}

}
