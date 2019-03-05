<%@page import="com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width=20% >
		<tr>
			<td>商品名称</td>
			<td>商品数量</td>
		</tr>
		
		<%
			Map<String,Integer> map = (Map<String,Integer>)session.getAttribute("cart");
			if(map==null){
				out.print("<tr><td col=2>购物车为空</td</tr>");
			}else{
				for(String name:map.keySet()){
					out.print("<tr><td>"+name+"</td><td>"+map.get(name)+"</td></tr>");
					
					System.out.println("商品名称是"+name+"数量是:"+map.get(name));
				}
			}
		
		%>
		
		<a href="/day1101/product_list.jsp">继续购物</a>
		<a href="/day1101/clearCart">清空购物车</a>
	</table>
	
	
</body>
</html>