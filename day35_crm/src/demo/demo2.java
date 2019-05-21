package demo;

import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.utils.MyCglibUtils;
import com.utils.MyProxyUtils;

/**
 * Spring 整合 junit  测试
 * 不用每次都建工厂
 * 属性注入
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo2 {
	
	@Resource(name="customerService")
	private CustomerService cs;
	@Resource(name="customerDao")
	private CustomerDao dao;
	
	@Test
	public void run1() {
		cs.save();
	}
	
	@Test
	public void run2() {
		CustomerDao proxy = MyProxyUtils.getProxy(dao);
		proxy.save();
		proxy.update();
	}
	
	@Test
	public void run3() {
		BookDaoImpl proxy = MyCglibUtils.getProxy();
		proxy.save();
		proxy.update();
	}
	
	/**
	 * AOP
	 */
	@Test
	public void run4() {
		dao.save();
		dao.update();
	}
	
}
