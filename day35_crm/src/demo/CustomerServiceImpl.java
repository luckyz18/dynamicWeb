package demo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class CustomerServiceImpl implements CustomerService{
	
/*	@Autowired   //默认按类型进行自动装配
	@Qualifier(value="customerDao")  //按名字  两个一起用
*/	
	@Resource(name="customerDao")   //java 提供的
	private CustomerDao dao;   //注解可以不提供set方法
	

	@Override
	public void save() {
		System.out.println("注解 service demo");
		dao.save();
	}

}
