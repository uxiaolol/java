<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'money.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <br>苍穹变金币库存 <br>
        <table border="1">
    <%
    	Collection<String[]> result = (Collection<String[]>)request.getAttribute("money");
		for (String[] row : result)
		{
		%>
		<tr><td><%=row[0]%></td><td><%=row[1]%><td><%=row[2]%><td><%=row[3]%></td><td style="width: 141px; "><%=row[4]%></td></tr>
		<%
		}
     %>
     </table>
  </body>
</html>
