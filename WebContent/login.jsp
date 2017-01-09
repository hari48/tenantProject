<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session = "false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FranConnectLogIn</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet">
</head>
<body>
Server Version: <%= application.getServerInfo() %><br> Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %> <br>
 	<div class="login-page">
		<div class="form">
			<s:form action="authenticate" method="post" id="loginForm" class="login-form" onsubmit="return checkValues()">
				<s:textfield name="userName" placeholder="LoginID" id="userName"></s:textfield>
				<s:password name="password" placeholder="Password" id="password"></s:password>
				<s:submit value="Login" id="button"></s:submit>
			</s:form>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
</body>
</html>