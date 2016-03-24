function pageFormSubmit() {
	$("#page-form").submit();
}

$(function() {

	var $realPath = $("#realPath").val().trim();
	var $currentPage = $("#currentPage").val().trim();
	var path = $realPath + "/";

	// According the currentPage to get the path
	switch (parseInt($currentPage)) {
	case 1:
		path = path + "StaffAction";
		break;
	case 2:
		path = path + "WebAppAction";
		break;
	case 3:
		path = path + "UserAction";
		break;
	case 4:
		path = path + "DepartmentAction";
		break;
	case 5:
		path = path + "RightAction";
		break;
	case 6:
		path = path + "RoleAction";
		break;
	case 7:
		path = path + "LogAction";
		break;
	}

	path = path + "_pageModelsByKeyword.action";

	var pageNo = $("#page-no-input").val().trim();

	// To set the page form's action attr
	$("#page-form").attr("action", path);

	// first page click
	$("#first-page").click(function() {
		$("#page-no-input").val("1");
		pageFormSubmit();
	});

	// pre page click
	$("#pre-page").click(function() {
		var pageCurrent = parseInt(pageNo) - 1;
		$("#page-no-input").val("" + pageCurrent);
		pageFormSubmit();
	});

	// next page click
	$("#next-page").click(function() {
		var pageCurrent = parseInt(pageNo) + 1;
		$("#page-no-input").val("" + pageCurrent);
		pageFormSubmit();
	});

	// last page click
	$("#last-page").click(function() {
		var pageCurrent = $("#total-page-number").val().trim();
		$("#page-no-input").val(pageCurrent);
		pageFormSubmit();
	});
});