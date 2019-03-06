<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath }/account">
		<table>
		<tr>
			<td>转账方</td>
			<td><input type="text" name="fromUser"></td>
		</tr>
		<tr>
			<td>收钱方</td>
			<td><input type="text" name="toUser"></td>
		</tr>
		<tr>
			<td>金额</td>
			<td><input type="text" name="money"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="确认转账"></td>
		</tr>
		</table>
 	</form>

</body>
</html>