package com.web.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.constant.Constant;
import com.convert.myconvert;
import com.domain.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.MD5Utils;
import com.utils.UUIDUtils;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class UserServlet extends BaseServlet {
	
	public String add(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("userservlet add 执行了");
		return null;		
	}
	
	/**
	 * 用户注册页面
	 * @return
	 */
	public String registerUI(HttpServletRequest request,HttpServletResponse response) {
		return "/jsp/register.jsp";   
	}
	
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception  
	 */
	public String register(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//封装数据
		User user = new User();
		//注册转换器		
		ConvertUtils.register(new myconvert(),Date.class);
		
		BeanUtils.populate(user, request.getParameterMap());   
	
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		//加密密码
		user.setPassword(MD5Utils.md5(user.getPassword()));
		
		//service
		UserService s = new UserServiceImpl();  //new一个实现类
		s.register(user);
		
		//转发
		request.setAttribute("msg", "注册成功，请激活邮件");	
		return "/jsp/msg.jsp";
		
	}
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @return
	 */
	public String active(HttpServletRequest request,HttpServletResponse response) {
		String code = request.getParameter("code");
		UserService s = new UserServiceImpl();
		try {
			User user = s.active(code);
			if (user == null) {
				request.setAttribute("msg", "重新激活");
			}else {
				request.setAttribute("msg", "激活成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/jsp/msg.jsp";
		
	}
	/**
	 * 用户登录页面  
	 */
	public String loginUI(HttpServletRequest request,HttpServletResponse response) {
		return "/jsp/login.jsp";   
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);
		
		//service
		UserService s = new UserServiceImpl();
		User user;
		try {
			user = s.login(username,password);
			if (user == null) {
				request.setAttribute("msg", "用户名密码不匹配");
				return "/jsp/login.jsp";
			}else {
				if (Constant.USER_IS_ACTIVE != user.getState()) {
					request.setAttribute("msg", "用户名未激活");
					return "/jsp/login.jsp";
				}
			}
			//将user放入session 重定向
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/");    
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @return
	 */
	public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/");
		
		return null;   
	}
	
	
	
	

}
