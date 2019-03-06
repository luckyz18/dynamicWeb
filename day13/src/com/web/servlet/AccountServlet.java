package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AccountService;
import com.service.AccountService4db;
import com.service.AccountService4tl;

/**
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter w = response.getWriter();
		
		//2接受参数
		String fromUser = request.getParameter("fromUser");
		String toUser = request.getParameter("toUser");
		String money = request.getParameter("money");
		System.out.println(fromUser+":"+toUser+":"+money);
		
		//3调用业务逻辑
		try {
//			new AccountService4tl().account(fromUser,toUser,money);
			new AccountService4db().account(fromUser, toUser, money);
		} catch (Exception e) {
			e.printStackTrace();
			w.print("转账失败");
			return;
		}
		
		//打印信息
		w.println("转账成功");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
