<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post.jsp</title>
</head>
<body>
	<input type="button" value="点我"   onclick ="btnClick()">
</body>

<script type="text/javascript">
	function btnClick() {
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
		xmlhttp.onreadystatechange= function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				alert(xmlhttp.responseText)
			}
		}
		
		//3 open
		xmlhttp.open("post","/day15_Ajax/ajax2");
		
		//设置请求头
		xmlhttp.setRequestHeader("content-type","application/x-www-form-urlencoded");  //post请求  请求参数还需要设置请求头
		//4 send
		xmlhttp.send("username=张三");
		
	}
</script>
<form action="" enctype="application/x-www-form-urlencoded"></form>
</html>