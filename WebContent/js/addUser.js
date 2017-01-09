function checkValues(){
	var name = document.getElementById("name").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var role = document.getElementById("role").value;
	$('.response').text("");
	$('.response').removeClass("ajaxresult");
	if (name != null && name != "" && username != null && username != "" && 
		password != null && password != "" && role != null && role != "-1") {
		$.post('addModifyUser', {
			name : name,
			username : username,
			password : password,
			role : role
		}, function(jsonResponse) {
			if(jsonResponse.code == "0")
			$('.response').text(jsonResponse.response);
			else if(jsonResponse.code == "1"){
			$('.response').addClass("ajaxresult");
			$('.response').text(jsonResponse.response);
			}
		});
	}
	else{
		if (name == null || name == "") {
			$('.response').text("Name cannot be empty !");
		}
		else if (username == null || username == "") {
			$('.response').text("LoginID cannot be empty !");
		}
		else if (password == null || password == "") {
			$('.response').text("Password cannot be empty !");
		}
		else if (role == null || role == "-1") {
			$('.response').text("Select a role for user !");
		}
	}
}

function checkValues2(){
	var name = document.getElementById("name").value;
	var username = document.getElementById("username").value;
	var role = document.getElementById("role").value;
	alert("name: "+name+" username: "+username+" role: "+role);
	$('.response').text("");
	$('.response').removeClass("ajaxresult");
	if (name != null && name != "" && username != null && username != "" && role != null && role != "-1") {
			$.post('addModifyUser', {
				name : name,
				username : username,
				role : role
			}, function(jsonResponse) {
				if(jsonResponse.code == "0")
				$('.response').text(jsonResponse.response);
				else if(jsonResponse.code == "1"){
				$('.response').addClass("ajaxresult");
				$('.response').text(jsonResponse.response);
				}
			});
		}
		else{
			if (name == null || name == "") {
				$('.response').text("Name cannot be empty !");
			}
			else if (username == null || username == "") {
				$('.response').text("LoginID cannot be empty !");
			}
			else if (role == null || role == "-1") {
				$('.response').text("Select a role for user !");
			}
		}
}

function cancelModify(){
	$('#addUserli').children().html("Add User");
	$('#content .current').empty();
	$('#tabs li, #content .current') .removeClass('current') .removeClass('fadeInLeft');
	$('#viewUsersli').addClass('current');
	var currentTab = $('#viewUsersli').children().attr('href');
	var tabId = $(currentTab).attr('id');
	$(currentTab).addClass('current fadeInLeft');
	$.ajax({
		type : "GET",
		url : "contentAction",
		data : {
			tab : tabId
		},
		success : function(result) {
			if ($(result).filter('title').text() == "FranConnectLogIn") {
				location.reload();
			} else {
				$(currentTab).html(result);
			}
		},
		error : function(xhr) {
		}

	});
}