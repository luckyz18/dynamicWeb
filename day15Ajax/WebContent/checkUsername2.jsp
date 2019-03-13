<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkUsername.jsp</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>  

<body>
	<form method="post" action="#">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"></td>
				<td><span id="username_msg"></span></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交 "  id="sub"></td>
			</tr>
		</table>
  </form>
</body>
<script type="text/javascript">
	$(function(){
		//事件
		$("input[name='username']").blur(function(){
			var $value = $(this).val();
			alert($value);
			$.get("/day15_Ajax/checkUsername","username="+$value, function(d){
				if(d==1){
					$("#username_msg").html("<font color='green'>用户名可以使用</font>");
					$("#sub").attr("disabled",false);
				}else{
					$("#username_msg").html("<font color='red'>用户名不可以使用</font>");
					$("#sub").attr("disabled",true); 
				}
			});
			
		});
	})
</script>

</html>