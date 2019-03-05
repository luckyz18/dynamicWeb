<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="username" value="tom"></c:set>
		<c:choose>
			<c:when test="${username==t }">
				username是t
			</c:when>
			<c:when test="${username==t1 }">
				username是t1
			</c:when>
			<c:when test="${username=='tom' }">
				username是tom
			</c:when>
			<c:otherwise>
				其他
			</c:otherwise>
		</c:choose>
	
</body>
</html>