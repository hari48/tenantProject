$('.message a').click(function() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
});

function checkValues() {
	var user = document.getElementById("userName").value;
	var pass = document.getElementById("password").value;
	if (user == null || user == "") {
		$('tr[errorfor="userName"]').remove();
		$('tr[errorfor="password"]').remove();
		$('.login-form tbody tr:first').before('<tr errorfor="userName"><td colspan="2" valign="top" align="center"><span class="errorMessage">Incorrect Login ID</span></td></tr>');
		return false;
	} else if (pass == null || pass == "" || pass.lenght < 6) {
		$('tr[errorfor="userName"]').remove();
		$('tr[errorfor="password"]').remove();
		$('.login-form tbody tr:first').after('<tr errorfor="password"><td colspan="2" valign="top" align="center"><span class="errorMessage">Incorrect Password</span></td></tr>');
		return false;
	} else {
		return true;
	}
}