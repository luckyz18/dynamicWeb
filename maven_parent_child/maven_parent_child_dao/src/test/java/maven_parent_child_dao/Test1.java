package maven_parent_child_dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dao.CustomerDao;



public class Test1{

	@Test
	public void findAll() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		CustomerDao customerDao = ac.getBean(CustomerDao.class);
		customerDao.findAll();
	}
}
