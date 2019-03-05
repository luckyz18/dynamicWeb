<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>获取域中数据--复杂 数据</title>
</head>
<body>
	<%
		//往request域中放入数组
		request.setAttribute("arr", new String[]{"a","b"});
		
		//往request域中放入list
		List list= new ArrayList();
		list.add("aaa");
		list.add("bbb");
		request.setAttribute("list", list );
		//往request域中放入map
		Map m = new HashMap();
		m.put("username","tom");
		request.setAttribute("map", m);
		
		//特殊符号
		request.setAttribute("user.age", 11);
		
		
	%>
	获取数组：
	<%= ((String[])request.getAttribute("arr"))[1] %>
	<br>
	el: ${arr[1]}
	<hr>
	
	获取list:
	<%= ((List)request.getAttribute("list")).get(1) %>
	<br>
	el: ${list.get(1)}
	el: ${list[1]}
	<br>
	
	
	获取map:
	<%=((Map)request.getAttribute("map")).get("username") %>
	<br>
	el: ${map.username}
	<br>
	
	特殊符号：
	el只能采取scope方式:
	<br>
	${requestScope["user.age"]}
	<%= request.getAttribute("user.age") %> 
	
	

	
	
</body>
</html>