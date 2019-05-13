<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--通配符方式访问 配置  -->
	<a href="${pageContext.request.contextPath }/linkman_save.action" >保存联系人</a><br>
	<a href="${pageContext.request.contextPath }/linkman_delete.action" >删除联系人</a><br>
	
	<!-- 配置动态访问 -->
	<a href="${pageContext.request.contextPath }/linkman!save.action" >保存联系人_动态访问</a><br>
	<a href="${pageContext.request.contextPath }/linkman!delete.action" >删除联系人_动态访问</a><br>
	
	
	<h3>注册页面</h3>
	<form action="${ pageContext.request.contextPath }/user_register.action"
		method="post">
		姓名:<input type="text" name="username" /><br /> 密码:<input
			type="password" name="password" /><br /> 
			<input type="submit" value="注册" />
	</form>
	
	<h3>封装数据-属性驱动方式</h3>
	<form action="${ pageContext.request.contextPath }/user_register1.action"
		method="post">
		姓名:<input type="text" name="username" /><br /> 密码:<input
			type="password" name="password" /><br /> 
			<input type="submit" value="注册" />
	</form>
	
	<h3>封装数据-属性驱动方式2-OGNL表达式-把数据封装到javabean中</h3>
	<form action="${ pageContext.request.contextPath }/user_register2.action"
		method="post">
		姓名:<input type="text" name="user.username" /><br /> 密码:<input
			type="password" name="user.password" /><br /> 
			<input type="submit" value="注册" />
	</form>
	
	<h3>封装数据-模型驱动</h3>
	<form action="${ pageContext.request.contextPath }/user_register3.action"
		method="post">
		姓名:<input type="text" name="username" /><br /> 密码:<input
			type="password" name="password" /><br /> 
			<input type="submit" value="注册" />
	</form>
	
	<h3>封装数据-集合封装-map</h3>
	<form action="${ pageContext.request.contextPath }/user_register4.action"
		method="post">
		姓名:<input type="text" name="map['one'].username" /><br /> 
		密码:<input type="password" name="map['one'].password" /><br /> 
			
		姓名:<input type="text" name="map['two'].username" /><br /> 
		密码:<input type="password" name="map['two'].password" /><br /> 
			<input type="submit" value="注册" />
	</form>
	
	<h3>封装数据-集合封装-list</h3>
	<form action="${ pageContext.request.contextPath }/user_register5.action"
		method="post">
		姓名:<input type="text" name="list[0].username" /><br /> 
		密码:<input type="password" name="list[0].password" /><br /> 
			
		姓名:<input type="text" name="list[1].username" /><br /> 
		密码:<input type="password" name="list[1].password" /><br /> 
			<input type="submit" value="注册" />
	</form>

</body>
</html>