<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>Add Food</title>
  </head>
  <body>
   <s:form action="food/food_addFood" method="post">
   <s:textfield name="food.foodname" label="名称"></s:textfield>
   <s:textfield name="food.uintprice" label="单价"></s:textfield>
   <s:submit value="提交"></s:submit>
   </s:form>
   <br>
  </body>
</html>
