<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示所有商品列表</title>
</head>
<body>

	 <table border="1" align="center" width="88%">
	 
	  <tr>
	  	<td colspan="7">
	  		<form action= "${pageContext.request.contextPath}/findProductByCondition" method="post">
	  			商品名称<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  			关键词<input type="text" name="kw">&nbsp;&nbsp;&nbsp;&nbsp;
	  			<input type="submit" value="查询">
	  		</form>
	  	</td>  	
	  	<td colspan="1" align="right"><input type="button" value="删除选中" onclick="delCheck()"></td>
	  </tr>
	  
	  
	  <tr>
	  	<td><input type="checkbox" onclick="checkAll(this)"></td>
		<th>pid</th>
		<th>pname</th>
		<th>market_price</th>
		<th>shop_price</th>
		<th>pimage</th>
		<th>pdesc</th>
		<th>操作</th>
	  </tr>
	  
	  <form action = "${pageContext.request.contextPath}/delCheckedProduct" method="post" id="formId">
		  <c:forEach items="${list}" var="p" >
		  	<tr> 
		  		<td><input type="checkbox" name="pid" value="${p.pid }"></td> 
				<td width="8%">${p.pid}</td>
				<td width="8%">${p.pname}</td>
				<td width="8%">${p.market_price}</td>
				<td width="8%">${p.shop_price}</td>
				<td width="8%"><img alt="" src="${pageContext.request.contextPath}/${p.pimage}" width="80"></td>
				<td>${p.pdesc}</td>
				<td width="10%">
					<a href="${pageContext.request.contextPath}/getProductById?pid=${p.pid}">修改</a>
					|
					<a href="javascript:void(0)" onclick="deleteP('${p.pid}')">删除</a>
				</td>
		  	</tr>     
		  </c:forEach>
	 </form>
  </table>
</body>

<script type="text/javascript">
	//删除一个商品
	function deleteP(obj){
		if (confirm("确定删除该商品吗？")) {
			location.href = "${pageContext.request.contextPath}/deleteProduct?pid="+obj;
		}
		
	}
	
	//全选、全不选
	function checkAll(obj) {
		var arr = document.getElementsByName("pid");
		for(var i=0;i<arr.length;i++){
			arr[i].checked = obj.checked;
		}
	}
	
	//删除选中
	function delCheck(){
		document.getElementById("formId").submit();  //form表单的 submit方法
	}
	
	
	
	
	
	
</script>
</html>