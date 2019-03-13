<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkUsername.jsp</title>
</head>
<body>
	<form method="post" action="#">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" onblur="checkUsername(this)"></td>
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

//  检测用户名是否被占用
	function checkUsername(obj) {
		//1.创建一个核心对象 XMLHttpRequest
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		 	 xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		
		//2 回调
		xmlhttp.onreadystatechange =function(){
			
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {				
				//alert(xmlhttp.responseText);
				
				if(xmlhttp.responseText == 1){
					document.getElementById("username_msg").innerHTML="<font color='green'>用户名可用</font>";
					document.getElementById("sub").disabled=false;
				}else{
					//alert("0~~");
					document.getElementById("username_msg").innerHTML="<font color='red'>用户名被占用</font>";
					document.getElementById("sub").disabled=true;
				}							
			}		
		}
		
		//3.open
		xmlhttp.open("get","${pageContext.request.contextPath}/checkUsername?username="+obj.value);
		//xmlhttp.open("get","/day15_Ajax/checkUsername?username="+obj.value);
		
		//4. send
		xmlhttp.send();
	}
</script>
</html>