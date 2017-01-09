<%@page import="java.util.Map"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sps" uri="/spstags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FranConnect</title>
<link href="<%=request.getContextPath()%>/css/tenantsList.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/colorbox.css" rel="stylesheet" type="text/css">
</head>
 <body>
<%
	 Map user = (Map)session.getAttribute("user");
     String role = (String)user.get("role");
     Boolean isAdmin = false;
     if(role.equalsIgnoreCase("admin")){
		isAdmin = true;
     }
%>	
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
   		<div id="addTenant" class="breadCum">
	    	<div style="float: left;">
		    <%
		    	String serverName = request.getParameter("serverName");
		    %>
		    	<b>Selected Server : <%=serverName%></b>
	    	</div>
	    	<div style="float: right;">
	    		<a id="showFilterLink" onclick="showFilters()" class="filterBtn"><b>Show Filters</b></a>
	    		<a id="hideFilterLink" onclick="hideFilters()" class="filterBtn"><b>Hide Filters</b></a>
	    	</div>    							
	    </div>
	    <div style="padding:  0px 10px">
	    	<div class="divSpacer"></div>
			<jsp:include page="filters.jsp"></jsp:include>
		</div>
		<div id="summarytable">
<!--  			<table id="example" class="display" width="100%" cellspacing="0" style="padding-bottom: 10px;">  -->
<!--  				<thead style="background-color: #4078C0; color: #FFFFFF">  -->
<!--  					<tr>  -->
<!--  						<th style="padding: 10px;width: 10px"><input type="checkbox"/></th>  -->
<!--  						<th>Build Name</th>  -->
<!--  						<th>Code Value</th>  -->
<!--  						<th>Build/Captivate URL</th>  -->
<!--  						<th>Build Type</th>  -->
<!--  						<th>Version</th>  -->
<!--  						<th>Parsing Email</th>  -->
<!--  						<th>Server Name</th>  -->
<!--  						<th>Tomcat Name</th>  -->
<!--  						<th>Last Modified</th>  -->
<!--  						<th>Action</th>  -->
<!--  					</tr>  -->
<!--  				</thead>  -->
<!--  				<tbody>  -->
<%--  											int n=200;
 						while(n>0){
<%--  					%>							  --%>
<!--  							<tr>  -->
<!--  								<td><input type="checkbox"/></td>  -->
<!--  								<td>T. Nixon</td>  -->
<!--  								<td>System Architect</td>  -->
<!--  								<td>Edinburgh</td>  -->
<!--  								<td>61</td>  -->
<!--  								<td data-order="1303686000">Mon 25th Apr 11</td>  -->
<!--  								<td data-order="320800">$320,800/y</td>  -->
<!--  								<td>sdasd</td>  -->
<!--  								<td>sdasd</td>  -->
<!--  								<td>sdasd</td>  -->
<!--  								<td>  -->
<%--  								<a href="javascript:void(0)" id="img<%=n%>" onclick="pulldownMenu(<%=n%>)">  --%>
<%--  									<img class="actionImage" src="<%=request.getContextPath()%>/img/edit.gif">  --%>
<!--  								</a>  -->
								
<%--  									<div id="dynamicMenu<%=n%>" class="myMenu-options">  --%>
<%--  								<span id="menu0<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">View User / Password</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">View Audit History</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">View Trigger History</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">Execute Query</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">View Logs</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">Download Files</span> <span  -->
<%--  									id="menu1<%=n%>" class="myMenu-item"  --%>
<!--  									onclick="javascript:void(0);">Run FJS Threads</span>  -->
<!--  							</div>  -->

<!--  						</td>  -->
<!--  							</tr>  -->
<%--  				
 							n--;
 						}
<%--  					%>  --%>
<!--  				</tbody>  -->
<!--  			</table> -->
			<sps:spsTable map="tenants" tagId="example" tagClass="display"
				scope="request">
				<sps:column displayName="CheckBox" dbName="" />
				<sps:column displayName="Build Name" dbName="BuildName" />
				<sps:column displayName="Code Value" dbName="CodeValue" />
				<sps:column displayName="Build/Captivate URL"
					dbName="Build/CaptivateURL" />
				<sps:column displayName="Build Type" dbName="BuildType" />
				<sps:column displayName="Version" dbName="Version" />
				<sps:column displayName="Parsing Email" dbName="ParsingEmail" />
				<sps:column displayName="Server Name" dbName="ServerName" />
				<sps:column displayName="Tomcat Name" dbName="TomcatName" />
				<sps:column displayName="Last Modified" dbName="LastModified" />
				<sps:column displayName="Action" dbName="Action" />
			</sps:spsTable>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		var isAdmin = <%=isAdmin%>;
	</script>
	<script src="<%=request.getContextPath()%>/js/jquery.colorbox.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tenantsList.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/table.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js "></script>
</body>
</html>