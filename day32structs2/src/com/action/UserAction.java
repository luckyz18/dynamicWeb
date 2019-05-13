package com.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	
	/*属性驱动 UGNL表达式*/
	/*private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
*/
	/*模型驱动方式*/
	private User user1 = new User();
	@Override
	public User getModel() {
		return user1;
	}
	
	/*把数据封装到 map 集合中,默认是属性驱动的方式*/
	private Map<String, User> map;
	public Map<String, User> getMap() {
		return map;
	}
	public void setMap(Map<String, User> map) {
		this.map = map;
	}
	
	/*把数据封装到 list 集合中,默认是属性驱动的方式*/
	private List<User> list;
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	
	
	
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			User existUser = new UserService().login(user);
			if (existUser != null) {
				System.out.println("登录成功");
				request.getSession().setAttribute("existUser", existUser);
				return SUCCESS;
			}else {
				return LOGIN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	/*test actionContext API*/
	public String register() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> map = context.getParameters();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String[] val = (String[]) map.get(key);
			System.out.println(key + ":" + Arrays.toString(val));
		}
		return NONE;
	}
	/*属性驱动的方式*/
	/*public String register2() {
		System.out.println(user);
		return null;
	}*/
	
	/*模型驱动的方式*/
	public String register3() {
		System.out.println(user1);
		return null;
	}
	/*用集合封装数据--map*/
	public String register4() {
		System.out.println(map);
		return null;
	}
	
	/*用集合封装数据--list*/
	public String register5() {
		System.out.println(list);
		return null;
	}


}
