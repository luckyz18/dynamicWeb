<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:forEach begin="1" end="10" var="k">
		${k }
	</c:forEach>
	<br>
	<c:forEach begin="1" end="20" step="2" var="i" varStatus="vs">
		${i }-${vs.count }-${vs.current}<br>
	</c:forEach>
	
	<!--复杂的foreach  -->
	list:<br>
	<%
		List l = new ArrayList();
		l.add(11);
		l.add(22);
		l.add(33);
		request.setAttribute("list", l);
		
		Set s = new HashSet();
		s.add(1);
		s.add(3);
		s.add(4);
		s.add(2);
		request.setAttribute("set", s);
		
		Map m = new HashMap();
		m.put("username","tom");
		m.put("age","18");
		request.setAttribute("map", m);
	%>
	
	<c:forEach items="${list }" var="i">
		${i}
	</c:forEach><br>
	
	set:<br>
	<c:forEach items="${set }" var="i" varStatus="vs">
		${i}-${vs.count}<br>
	</c:forEach><br>
	
	map:<br>
	<c:forEach items="${map }" var="i" varStatus="">
		${i.key}-${i.value}}<br>
	</c:forEach><br>
	
</body>
</html>