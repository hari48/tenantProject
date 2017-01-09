<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Map" %>
<link href="<%=request.getContextPath()%>/css/addUser.css" rel="stylesheet">
<%
	String name = "";
	String userName = "";
	String role = "-1";
	if("modify".equals(request.getParameter("event"))){
		Map modifyUser = (Map)request.getAttribute("modifyUser");
		name = (String)modifyUser.get("name");
		userName = (String)modifyUser.get("username");
		role = "1";
	}
%>
<div class="form">
	<div class="response"></div>
	<s:textfield id="name" label="Name" name="name"></s:textfield>
	<s:textfield id="username" label="LoginID" name="username"></s:textfield>
	<%if(!"modify".equals(request.getParameter("event"))){ %>
		<s:password id="password" label="Password" name="password"></s:password>
	<%} %>
	<s:select id="role" label="Role" name="role"
		headerKey="-1" headerValue="Select role"
		list="#{'1':'Admin', '2':'FSS', '3':'Guest'}"/>
	<%
	if("modify".equals(request.getParameter("event"))){
	%>
		<s:submit value="Save" onclick="checkValues2()" id="button"></s:submit>
		<s:submit value="Cancel" onclick="cancelModify()" id="button"></s:submit>
	<%} 
	else{
	%>
	<s:submit value="Submit" onclick="checkValues()" id="button"></s:submit>
	<%} %>
</div>
<script>
$(function() {
	 document.getElementById("name").value='<%=name%>';
	 document.getElementById("username").value='<%=userName%>';
	 document.getElementById("role").headerKey='<%=role%>';
	 
});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addUser.js"></script>