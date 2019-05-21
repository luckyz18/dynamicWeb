package com.demo_tx_AOP;

public interface AccountDao {
	public void out(String out,Double money);
	public void in(String in,Double money);
}
