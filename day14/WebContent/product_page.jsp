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
	  
		<c:forEach items="${pb.list}" var="p">
		  	<tr>   		  		 
				<td width="8%">${p.pid}</td>
				<td width="8%">${p.pname}</td>
				<td width="8%">${p.market_price}</td>
				<td width="8%">${p.shop_price}</td>
				<td width="8%"><img alt="" src="${pageContext.request.contextPath}/${p.pimage}" width="80"></td>
				<td>${p.pdesc}</td>
		  	</tr> 
	  	</c:forEach>
	 
  </table>
  
  <center>
  	<c:if test="${pb.currentPage!=1 }">
	  	<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=1">首页</a>
	  	<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pb.currentPage - 1}">上一页</a>
  	</c:if>
  	
  	<c:forEach begin="1" end="${pb.totalPage}" var="n">
  		<c:if test="${pb.currentPage != n}">
  			<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${n}">${n}</a>
  		</c:if>
  		<c:if test="${pb.currentPage == n}">
  			${n}
  		</c:if>
  	</c:forEach>
  	
  	<c:if test="${pb.currentPage!= pb.totalPage }">
  		<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pb.currentPage + 1}">下一页</a>
  		<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pb.totalPage}">尾页</a>
  	</c:if>
  	
  	第${pb.currentPage}页/共${pb.totalPage}页
  </center>
  
  
</body>
</html>