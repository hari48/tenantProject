$(document) .ready( function() {
	var numberOfli = $('#tabwrap ul li').length;
	$('#tabs li').width(100/numberOfli +'%');
	
	$('#tabs li a') .click(
		function(e) {
			$('#addUserli').children().html("Add User");
			$('#content .current').empty();
			$('#tabs li, #content .current') .removeClass('current') .removeClass('fadeInLeft');
			$(this).parent().addClass('current');
			var currentTab = $(this).attr('href');
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
			e.preventDefault();
		});
	
});