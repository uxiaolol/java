<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- 	<script  language="javascript">
	function delCqb(deviceid){
		window.location.href="<%=basePath%>servlet/game?action=cqbDelete&deviceid="+deviceid;
	}
	function myrefresh(){
	   window.location.reload();
	}
	setTimeout('myrefresh()',1000*5*60); //指定1秒刷新一次
	
	</script> 	 --%>
  </head>
  
  <body>
    <br>test！<br><br>
	
<%--     <table border="1">
    <%
    	Collection<String[]> result = (Collection<String[]>)session.getAttribute("report");
		for (String[] row : result)
		{
		%>
		<tr><td><%=row[0]%></td><td><%=row[1]%><td><%=row[2]%><td><%=row[3]%></td><td style="width: 50px; "><%=row[4]%></td>
		<td><button  type="button"  onclick="delCqb('<%=row[0]%>')"  >delete</button></td>
		</tr>
		<%
		}
     %>
     </table> --%>
  </body>
</html>

