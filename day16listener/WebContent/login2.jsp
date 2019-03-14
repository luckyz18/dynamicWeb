<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login2.jsp</title>
</head>
<body>
 <form method="get" action="${pageContext.request.contextPath }/loginEncode">
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="username" value=""></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text" name="password"></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="提交"></td>
		</tr>
	</table>
  </form>
</body>

</html>