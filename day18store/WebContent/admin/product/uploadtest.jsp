<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/uploadtest" method="post" enctype="multipart/form-data">
		姓名：<input type="text" name="username"><br>
		文件：<input type="file" name="f"><br>
		<input type="submit"><br>
	</form>
</body>
</html>