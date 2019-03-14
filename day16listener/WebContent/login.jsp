<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form method="post" action="${pageContext.request.contextPath }/login">
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
			<td><input type="checkbox" name="autoLogin" value="ok">自动登陆</td>
			<td><input type="checkbox" name="savename" value="ok">记住名户名</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="提交"></td>
		</tr>
	</table>
  </form>
</body>
<script type="text/javascript">
	// 页面加载的时候 就去找cookie   onload
    onload = function(){
    	var s = "${cookie.savename.value}";
    	//alert(s);
    	s  =decodeURI(s);
    	alert(s);
    	document.getElementsByName("username")[0].value = s;
	 	
    }
	
	
</script>
</html>