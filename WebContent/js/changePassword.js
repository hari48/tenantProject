function checkPasswords() {
	var pass1 = document.getElementById("pass1").value;
	var pass2 = document.getElementById("pass2").value;
	$('.response').text("");
	$('.response').removeClass("ajaxresult");
	if (pass1 != null && pass1 != "" && pass2 != null && pass2 != "" && (pass1.trim() == pass2.trim())) {
		var r = confirm("Are you sure you want to change password? ");
		if (r) {
			$.post('ajaxChangePasswordAction', {
				userId : userID,
				newPass : pass1
			}, function(jsonResponse) {
				$('.response').text(jsonResponse.passChange);
				$('.response').addClass("ajaxresult");
			});
		} else
			return false;
	} else {
		if (pass1 == null || pass1 == "") {
			$('.response').text("Password cannot be empty");
		} else {
			$('.response').text("Passwords do not match");
		}
		return false;
	}
}