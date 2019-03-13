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

	<div align="center">
		<h2>搜索</h2>
		<div>
			<input type="text" name="kw" id="kId">  <input type="button" value="搜索" >
		</div>
		
		<div id ="did" style="border: 1px solid red; width:171px ;  position: relative;left: -24px; display: none" ></div>
	</div>
	
</body>

<script type="text/javascript">
	
	$(function(){
		//事件  发送ajax
		$("#kId").keyup(function(){
			//获取文本框的值
			$value = $(this).val();
			
			//内容为空的时候不发送ajax
			if ($value != null || $value !='') {
				//清空div
				$("#did").html("");
				
				/* $.post("/day15_Ajax/searchKw4Ajax","kw="+$value,function(d){
					if (d!='') {
						var arr = d.split(",");
						 $(arr).each(function(){
							 $("#did").append($("<div>"+this+"</div>"));
						}); 
						//显示div
						$("#did").show();
					}
				}); */
				
				//用json改写上边方法
				$.post("/day15_Ajax/searchKw4Ajax","kw="+$value,function(d){
					if (d!='') {
						 $(d).each(function(){
							 $("#did").append($("<div>"+this+"</div>"));
						}); 
						//显示div
						$("#did").show();
					} 
				},"json");
				
				
			}else{
				$("#did").hide();
			}
			
		});  
		
	})
	
	
	
</script>
</html>