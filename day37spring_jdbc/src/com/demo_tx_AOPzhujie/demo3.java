package com.demo_tx_AOPzhujie;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class demo3 {
	@Resource(name="accountService")
	private AccountService as;
	
	@Test
	public void run1() {
		as.pay("测试1", "测试2", 10.0);
		
		
		
	}
}
