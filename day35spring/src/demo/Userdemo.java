package demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.domain.UserTest;
import com.service.UserServiceImpl;

public class Userdemo {

	@Test
	public void run1() {
		 // 使用Spring的工厂:
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 // 通过工厂获得类
	   UserServiceImpl us =  (UserServiceImpl) applicationContext.getBean("userService");
	   us.save();
	}
	
	/*集合注入*/
	@Test
	public void run2() {
		 // 使用Spring的工厂:
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 // 通过工厂获得类
	    UserTest user= (UserTest) applicationContext.getBean("user");
	    System.out.println(user);
	  
	}
	
}
