$(function() {
	$('#example').DataTable({
		"order" : [ [ 1, "desc" ] ],
		"pagingType" : "simple",
		"lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Show All" ] ],
		"columnDefs" : [ {
			"searchable" : false,
			"orderable" : false,
			"targets" : [ 0, 5 ]
		} ]
	});
	$('#tableTitle').html('User Summary');
});

function modifyUser(userid){
	$('#content .current').empty();
	$('#tabs li, #content .current') .removeClass('current') .removeClass('fadeInLeft');
	$('#addUserli').addClass('current');
	$('#addUserli').children().html("Modify User");
	var currentTab = $('#addUserli').children().attr('href');
	var tabId = $(currentTab).attr('id');
	$(currentTab).addClass('current fadeInLeft');
	$.ajax({
		type : "POST",
		url : "modifyUserFormAction",
		data : {
			user_id : userid,
			event   : 'modify'
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