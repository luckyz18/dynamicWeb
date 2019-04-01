<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>  

<script type="text/javascript">
	
	$(function(){
		
		//页面加载时 出现所有的省
		$.get("/day15_Ajax/searchProvince",function(d){
			$(d).each(function(){
				//加到省下
				$("#proId").append($("<option value="+this.provinceid+">"+this.name+"</option>")); 				
			});  
			
		},"json");   //要写上json  后台以json格式返回 前台要这么接收  each 才不报错
		
		
		//事件  显示相应的市
		$("#proId").change(function(){
			//清空
			$("#cityId").html("<option>-请选择-</option>");
			
			//获取省 id
			var $pid =$(this).val();
			//alert($pid);
			$.get("/day15_Ajax/searchCity",{"pid":$pid},function(d){
				$(d).each(function(){
					$("#cityId").append($("<option value="+this.cityid+">"+this.name+"</option>")); 
				});
				
				
			},"json");
			
		});
	
	})
</script>


<body>
	<select id="proId">
		<option>-请选择-</option>
		<!-- 
			<option value="1">北京</option>
		 -->
	</select>
	
	<select id="cityId">
		<option>-请选择-</option>
	</select>
</body>
</html>