<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Refresh" content="2;url=food/food_showFood">
    <title>My JSP 'index.jsp' starting page</title>
	
  </head>
  
  <body>
    成功添加美食！ <br>
    
  </body>
</html>
