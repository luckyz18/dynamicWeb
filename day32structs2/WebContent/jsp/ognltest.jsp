<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ognl值栈 <br>
	<!-- 
		<s:property value="[0].top"/>
	 -->
	 <!-- 
	 	map取值 
	 	set(user) 
		<s:property value="user.username"/>
		<s:property value="user.password"/>
	  -->
	  
	  <!--  
	  	  push(user)
		  <s:property value="username"/>
	  -->
	 
	 
	 <!-- 
	 	栈顶是list
	 	 [0].top取得是list
		 <s:property value="[0].top[0].username"/> 
		 <s:property value="[0].top[1].username"/> 
	  -->
	  <!-- 
	  	栈顶是list 迭代
	  	vs.push(ulist);
		  <s:iterator value="[0].top">
		   		<s:property value="username"/>
		   </s:iterator>
	   -->
	  
	  
	  <!--
	  	[0].top取得是map
	  	栈顶是map
	  	vs.set("ulist", ulist);
		  <s:property value="ulist[0].username"/>
		  
	  -->
		    <s:iterator value="ulist" var="user">
	  			<s:property value="#user.username"/>
	  			<s:property value="#user.password"/>
	 	    </s:iterator>
	   <!-- 
	  	迭代标签
	  		属性：
	  			value 要迭代的集合
	  			var 迭代过程中 要遍历的对象
	  				var写上，把迭代产生的对象默认压入到context栈 (map)中， 从context取值，加#
	  				var不写，默认把迭代产生的对象压入到root栈中
	  				
	    <s:iterator value="ulist" >
		  	<s:property value="username"/>
		  	<s:property value="password"/>
	  	</s:iterator>
	
	  -->
	  <!-- el表达式也可以从值栈中取 值，,原因是 对request进行了增强，
	  	代理模式，
	  	增强了request的 getAttribute
	  	访问request范围的数据时，如果数据找不到，去值栈中找 
	  	 request对象 具备访问值栈数据的能力 （查找root的数据）
	  	 
	  <c:forEach items="${ulist }" var="user">
	  	${ user.username}  --  ${user.password }
	  </c:forEach>
	  -->
	  
	  
	<s:debug/>
</body>
</html>