<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=utf-8"%>
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
<script type="text/javascript" src="jquery/jquery-1.12.3.min.js">
  </script>
<script type="text/javascript" src="jquery/jquery.copy.js">//引入jquery框架
  </script>
<script type="text/javascript" src="jquery/ZeroClipboard.js">//引入jquery框架
  </script>
<script type="text/javascript" src="jquery/ZeroClipboard.min.js">//引入jquery框架
  </script>
<script language="javascript">
	function delTouchelf(deviceid){
		window.location.href="<%=basePath%>servlet/game?action=delTouchelf&code="+deviceid;
		//alert(deviceid);
	}
	
	function copyTouchelf(code){ 
	
	}
	</script>

</head>

<body>
	<br>崩坏3账号
	
	<table border="1">
	<tr><td>账号</td><td>钻石</td><td>等级</td><td>操作</td></tr>
		<%
	    	Collection<String[]> result = (Collection<String[]>)request.getAttribute("touchelf");
			for (String[] row : result)
			{
			%>
	
		<tr><td><%=row[0]%></td><td><%=row[1]%></td><td><%=row[2]%></td><td><button type="button" onclick="delTouchelf('<%=row[0]%>')"style="width: 60px; height: 28px">删除</button></td></tr>
	
		<%
			}
	     %>
  	</table>
</body>
</html>

