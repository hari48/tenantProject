$(function() {
	$('#example').DataTable({
		"order" : [ [ 1, "desc" ] ],
		"pagingType" : "simple",
		"lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Show All" ] ],
		"columnDefs" : [ {
			"searchable" : false,
			"orderable" : false,
			"targets" : [ 0, 10 ]
		} ]
	});
	$('#tableTitle').html('Tenant Summary');
	
	if(isAdmin){
		$('#titledivider').css("display","")
		$('#buttonAddTitle').html('Add Tenant');
		$('#buttonAdd').css("display","").click(function(event){
			$.fn.colorbox({
				width : 600,
				height : 600,
				iframe : true,
				scrolling : false,
				href : "addTenant",
				overlayClose : false
			});
		});	
	}	
});

function hideFilters() {
	document.getElementById('main').style.display = "none";
	document.getElementById('hideFilterLink').style.display = "none";
	document.getElementById('showFilterLink').style.display = "block";
}
function showFilters() {
	var x = document.getElementById('main').style.display = "block";
	document.getElementById('hideFilterLink').style.display = "block";
	document.getElementById('showFilterLink').style.display = "none";
}
$(function() {
	document.getElementById('hideFilterLink').style.display = "block";
	document.getElementById('showFilterLink').style.display = "none";
});

$('.selectpicker').multiselect({
	includeSelectAllOption : true,
	enableClickableOptGroups : true,
	onSelectAll : function() {
		('onSelectAll triggered.');
	}
});
