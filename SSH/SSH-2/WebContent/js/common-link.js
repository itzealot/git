/**
 * To remove all class by className value
 */
function removeAllClass(className) {
	$("." + className).removeClass("" + className);
}

$(function() {
	var $currentPage = $("#currentPage").val().trim();

	// remove class
	removeAllClass("select");

	switch (parseInt($currentPage)) {
	case 1:
		$("#staff-manage").addClass("select");
		break;
	case 2:
		$("#webapp-manage").addClass("select");
		break;
	case 3:
		$("#user-manage").addClass("select");
		break;
	case 4:
		$("#dept-manage").addClass("select");
		break;
	case 5:
		$("#right-manage").addClass("select");
		break;
	case 6:
		$("#role-manage").addClass("select");
		break;
	case 7:
		$("#log-manage").addClass("select");
		break;
	}
});