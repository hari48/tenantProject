<%@page import="org.json.JSONArray" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FranConnect</title>
</head>
 <body>
 	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
    <%
    	String jsonPersons = "[" + "[ \"Dallas\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"DayTon\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Springfield\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Chicago\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Dover\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Bristol\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Yuma\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Lomita\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ],"
    			+ "[ \"Bunnell\",\"NorthStar\",[\"tenant11\",\"tenant12\"] ]" + "]";
    	JSONArray serverArray = new JSONArray(jsonPersons);
    	JSONArray serverDetail;
    	String serverName;
    	String buildType;
    	JSONArray tenantArray;
    	boolean newRowStarted = false;
    	for (int i = 0; i < serverArray.length(); i++) {
    		serverDetail = serverArray.getJSONArray(i);
    		serverName = (String) serverDetail.get(0);
    		buildType = (String) serverDetail.get(1);
    		tenantArray = (JSONArray) serverDetail.get(2);
    		if (i % 4 == 0) {
    			if (newRowStarted) {
    %>
		</div>
    <%		
    		}
    		newRowStarted=true;
    %>
		<div class="row">
    <%		
    	}
    %>
       		<div class="col-sm-3 col-lg-3">
    			<div class="dash-unit" style="text-align: center;">
      				<p style="color: #4078C0;text-align: center;font-size: 30px"><b><%=serverName%></b></p>
      				<img style="width: 116px; height: 109px;" src="<%=request.getContextPath()%>/img/server.jpg">
      				<p style="color: #000000;text-align: center;font-size: 14px ; margin-top: 10px;">Build : <b><%=buildType%></b></p>
      				<p style="color: #000000;text-align: center;font-size: 14px">Number of Tenants : <b><%=tenantArray.length()%></b></p>
      				<button class="button" onclick="proceed('<%=serverName%>')"><span>Proceed </span></button>
				</div>
	    	</div> 
    <%		
    }
    %>
     	</div>
    </div> 
	
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		function proceed(a){
			document.serverForm.serverName.value=a;
			document.serverForm.submit();
		}
	</script>
	<form action="tenantList" name="serverForm" method="post">
		<input type="hidden" name="serverName">
	</form>
</body>
</html>