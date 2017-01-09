<%@page import="java.util.Map, java.util.Iterator"%>
<link href="<%=request.getContextPath()%>/css/table.css"
	rel="stylesheet" type="text/css">
<%@ taglib prefix="sps" uri="/spstags" %>
<%
  	Map mainMap = (Map) request.getAttribute("allUsers");
  	Iterator itr = mainMap.keySet().iterator();
	String colsList = "role,role_id";
%>
<div id="summarytable">
	<%-- <table id="example" class="display" width="100%" cellspacing="0"
		style="padding-bottom: 10px;">
		<thead style="background-color: #4078C0; color: #FFFFFF">
			<tr>
				<th style="padding: 10px; width: 10px"><input type="checkbox" /></th>
				<th>UserName</th>
				<th>Name</th>
				<th>Role</th>
				<th>Role ID</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
				int n = mainMap.size();
				while (itr.hasNext()) {
					Map userMap = (Map) mainMap.get(itr.next());
			%>
			<tr>
				<td><input type="checkbox" /></td>
				<td><%=(String) userMap.get("username")%></td>
				<td><%=(String) userMap.get("name")%></td>
				<td><%=(String) userMap.get("role")%></td>
				<td><%=(String) userMap.get("role_id")%></td>
				<td><a href="javascript:void(0)" id="img<%=n%>"
					onclick="pulldownMenu(<%=n%>)"> <img class="actionImage"
						src="<%=request.getContextPath()%>/img/edit.gif">
				</a>
					<div id="dynamicMenu<%=n%>" class="myMenu-options">
						<span id="menu0<%=n%>" class="myMenu-item"
							onclick="javascript:void(0);">Modify</span> <span
							id="menu1<%=n%>" class="myMenu-item"
							onclick="javascript:void(0);">Delete</span>
					</div></td>
			</tr>
			<%
				n--;
				}
			%>
		</tbody>
	</table>--%>
	<sps:spsTable map="allUsers" tagId="example" tagClass="display" scope="request">
		<sps:column displayName="CheckBox" dbName="" />
		<sps:column displayName="Username" dbName="username"/>
		<sps:column displayName="Name" dbName="name"/>
		<sps:column displayName="Role" dbName="role"/>
		<sps:column displayName="Role_Id" dbName="role_id"/>
		<sps:column displayName="Action" dbName="" />
	</sps:spsTable>
</div>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/table.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/viewUsers.js"></script>