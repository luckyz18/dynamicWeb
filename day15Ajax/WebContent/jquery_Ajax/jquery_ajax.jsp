<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>   
<body>
	<input type="button" id="btn" value= "点我">
</body>
<script type="text/javascript">
	$(function(){
		
		$("#btn").click(function(){
			var url = "${pageContext.request.contextPath}/jqueryAjax";
			//var params =("username = 张三 ") ;
			var params = {"username":"赵四 "};
			
			//load方式
			/* $(this).load(url,params,function(d){
				alert(d);
			}) */
			
			//get
			/* $.get(url,params,function(d){
				alert(d.msg)
			},"json")
			 */
			 
			//post
			$.post(url,params,function(d){
				alert(d.msg)
			},"json")
			//ajax
			
			
		})
	})
</script>
</html>