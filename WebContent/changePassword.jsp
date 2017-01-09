<%@page import="java.util.Map"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="<%=request.getContextPath()%>/css/changePassword.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<%
	Map user = (Map)session.getAttribute("user");
	int userID = (int)user.get("user_id");
%>
<div class="form">
	<div class="response"></div>
	<s:password id="pass1" label="New Password"></s:password>
	<s:password id="pass2" label="Confirm Password"></s:password>
	<s:submit value="Change Password" onclick="checkPasswords()" id="button"></s:submit>
</div>
<script>
	var userID = <%=userID%>;
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/changePassword.js"></script>