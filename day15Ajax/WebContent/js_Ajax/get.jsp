<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>get.jsp</title>
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
		xmlhttp.open("get","/day15_Ajax/ajax2?username=张三");
		
		//4 send
		xmlhttp.send();
		
	}
</script>
</html>