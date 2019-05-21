package demo;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

public class Demo1 extends ActionSupport{
	@Test
	public void run1() {
		/*ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		CustomerService cs = (CustomerService) context.getBean("customerService");*/
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService cs = (CustomerService) ac.getBean("customerService");
		cs.save();
	}
	
//	error
//	@Test  
//	public void run2() {
//		ServletContext servletContext = ServletActionContext.getServletContext();
//		
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		CustomerService cs = (CustomerService) context.getBean("customerService");
//		
//		cs.save();
//	}
	
	
}
