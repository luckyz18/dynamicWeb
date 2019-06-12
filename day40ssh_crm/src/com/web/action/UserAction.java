package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import com.utils.FastJsonUtil;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = 1L;

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 	注册校验用户名是否存在
	 * @return
	 */
	public String checkCode() {
		User u = userService.checkCode(user.getUser_code());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			if (u != null) {
				writer.print("no");
			}else {
				writer.println("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 注册
	 * @return
	 */
	public String regist() {
		System.out.println("注册/。。。。");
		userService.save(user);
		return LOGIN;
	}
	
	/**
	 * 	登录 
	 * 	密码加密
	 * 	状态
	 * @return
	 */
	public String login() {
		User exitUser = userService.login(user);
		if (exitUser == null) {
			return LOGIN;
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("user", exitUser);;
			return "login_ok";
		}
	}
	
	/**
	 * 	登出
	 * @return
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return LOGIN;
	}
	
	public String findAll() {
		List<User> list = userService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return null;
	} 
	
}
