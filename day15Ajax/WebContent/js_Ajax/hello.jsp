<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" value="点我"   onclick ="btnClick()">
	
</body>
<script type="text/javascript">
	function btnClick() {
		//alert(111);

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
		
		//2.编写一个回调函数
		xmlhttp.onreadystateChange= function(){
			alert("hehe");
			
		}
		
		//3.编写请求方式 和请求的路径（open操作	）
		xmlhttp.open("get","${pageContext.request.contextPath}/ajax1");
		
		//4.发送请求（send操作）
		xmlhttp.send();
	
		
	}
</script>
</html>