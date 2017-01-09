<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/css/settings.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Settings</title>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
<div class="container">	
 <div id="tabwrap"> 
  <ul id="tabs">
   <li id="changePasswordli" class="current"><a href="#changePassword">Change Password</a></li> 
   <li id="viewUsersli"><a href="#viewUsers">View Users</a></li>
   <%
	 Map user = (Map)session.getAttribute("user");
     String role = (String)user.get("role");
     if(role.equalsIgnoreCase("admin")){
   %>
  		<li id="addUserli"><a href="#addUser">Add User</a></li>
   <%} %>
  </ul> 
  <div id="content"> 
    <div id="changePassword" class="current animated"><jsp:include page="changePassword.jsp"></jsp:include></div>
    <div id="viewUsers" class="animated"></div>
    <div id="addUser" class="animated"></div>
  </div>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/settings.js"></script>
</body>
</html>