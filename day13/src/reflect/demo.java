package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import com.domain.Person;

public class demo {
	
	@Test
	public void f1() throws Exception {
		Class clazz = Class.forName("com.domain.Person");
		Person p = (Person) clazz.newInstance();
		Method m = clazz.getMethod("eat");
		m.invoke(p);
	}
	
	@Test
	/*获取私有的方法  **/
	public void f2() throws Exception {
		Class clazz = Class.forName("com.domain.Person");
		Person p = (Person) clazz.newInstance();
		Method m = clazz.getDeclaredMethod("sleep");
		m.setAccessible(true);
		m.invoke(p);
	}
	
	@Test
	public void f3() throws Exception {
		Class clazz = Class.forName("com.domain.Person");
		Person p = (Person) clazz.newInstance();
		Method m = clazz.getDeclaredMethod("sleep", String.class);
		m.setAccessible(true);
		String s = (String) m.invoke(p, "lili");
		System.out.println(s);
	}

	@Test
	public void f4() throws Exception {
		Class clazz = Class.forName("com.domain.Person");
		Constructor c = clazz.getConstructor();
		Person p = (Person) c.newInstance();
		
	}
	
	
	
}
