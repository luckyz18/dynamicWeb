package com.demo_tx;

public interface AccountDao {
	public void out(String out,Double money);
	public void in(String in,Double money);
}
