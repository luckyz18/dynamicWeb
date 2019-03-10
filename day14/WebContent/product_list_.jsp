<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	 <table border="1" align="center" width="88%">
	  <tr>
		<th>pid</th>
		<th>pname</th>
		<th>market_price</th>
		<th>shop_price</th>
		<th>pimage</th>
		<th>pdesc</th>
		<th>操作</th>
	  </tr>
	  
	  <c:forEach items="${list}" var="p" >
	  	<tr>   
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
	 
  </table>
</body>

<script type="text/javascript">
	function deleteP(obj){
		if (confirm("确定删除该商品吗？")) {
			location.href = "${pageContext.request.contextPath}/deleteProduct?pid="+obj;
		}
		
	}
</script>
</html>