<%@page import="com.utils.UUIdUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//放入令牌，以免多次提交
		session.setAttribute("s_token", UUIdUtils.getCode());
		pageContext.setAttribute("r_token",session.getAttribute("s_token") );
	%>

	<form method="post" action="${pageContext.request.contextPath}/addProduct">
		<input type="hidden" name="r_token" value="${r_token}">
		
		<table border="1" align="center" width="40%">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="pname"></td>
			</tr>
			<tr>
				<td>市场价</td>
				<td><input type="text" name="market_price"></td>
			</tr>
			<tr>
				<td>商城价</td>
				<td><input type="text" name="shop_price"></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><input type="text" name="pdesc"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="保存"></td>			
			</tr>
		</table>
	  </form>
</body>
</html>