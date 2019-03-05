<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="/day12/jsp/page.jsp">page指令</a><hr>
	<a href="/day12/jsp/include/include.jsp">include指令</a><hr>
	<a href="/day12/jsp/pageContext.jsp">pageContext操作其他预对象</a><hr>
	<a href="/day12/jsp/action/forward.jsp">jsp-forward</a><hr>
	<a href="/day12/jsp/action/include.jsp">jsp-include</a><hr>
	<a href="/day12/el/demo1.jsp">el简单表达式</a><hr>
	<a href="/day12/el/demo2.jsp">el复杂表达式</a><hr>
	<br>
	<a href="/day12/el/demo3.jsp">javabean</a><hr>
	
	<a href="/day12/el/demo4.jsp">执行运算</a><hr>
	
	<a href="/day12/el/demo5.jsp?username=tom&hobby=eat&hobby=sleep">el内置对象-带请求参数</a><hr>
	<a href="/day12/el/cookie.jsp">el内置对象-cookie</a><hr>
	
	<a href="${pageContext.request.contextPath}/el/demo6.jsp">el内置对象-pagecontext</a><hr>
	
	<a href="${pageContext.request.contextPath}/jstl/if.jsp">c:if</a><br>
	
	<a href="${pageContext.request.contextPath}/jstl/foreach.jsp">core_foreach</a><br>
	
	<a href="${pageContext.request.contextPath}/jstl/choose.jsp">core_set choose</a><br>
	
	<a href="${pageContext.request.contextPath}/jstl/fn.jsp">fn</a><br>
	
</body>
</html>