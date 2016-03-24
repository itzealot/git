/**
 * form submit by actionName where element's id is common-form
 * 
 * @param actionName
 */
function formSubmit(actionName) {
	$("#common-form").attr("action", actionName);
	$("#common-form").submit();
}
/**
 * To submit the form where the form's id is formId and the action's name is
 * actionName.<br />
 * 
 * @param formName
 * @param actionName
 */
function formSubmitById2(formId, actionName) {
	$("#" + formId).attr("action", actionName);
	$("#" + formId).submit();
}
/**
 * To submit the form where the form's id is formId.<br />
 * 
 * @param formName
 */
function formSubmitById(formId) {
	$("#" + formId).submit();
}

/**
 * To get the value by element's id
 * 
 * @param id
 * @returns
 */
function getValue(id) {
	return $("#" + id).val().trim();
}
/**
 * To set the value by element's id and value
 * 
 * @param id
 * @returns
 */
function setValue(id, value) {
	$("#" + id).val(value);
}
/**
 * To get the text by element's id
 * 
 * @param id
 * @returns
 */
function getText(id) {
	return $("#" + id).text().trim();
}
/**
 * To set the text by element's id and value
 * 
 * @param id
 * @returns
 */
function setText(id, value) {
	$("#" + id).text(value);
}
/**
 * To get the html by element's id
 * 
 * @param id
 * @returns
 */
function getHtml(id) {
	return $("#" + id).html().trim();
}
/**
 * To set the html by element's id and value
 * 
 * @param id
 * @returns
 */
function setHtml(id, value) {
	$("#" + id).html(value);
}

function searchCommon(actionName) {
	// get value by id is 'keyword'
	var $value = getValue("keyword");

	// set value by id and value
	setValue("keyword-input", $value);

	// form submit by form's id
	formSubmitById2("search-form", actionName);
}
$(function() {

	// hide the search div
	// $("#div-search").hide();

	// staffs search link a click
	$("#staffs-search").click(function() {
		var actionName = "StaffAction_pageModelsByKeyword.action";
		searchCommon(actionName);
	});

	// depts search link a click
	$("#depts-search").click(function() {
		var actionName = "DepartmentAction_pageModelsByKeyword.action";
		searchCommon(actionName);
	});
	// roles search link a click
	$("#roles-search").click(function() {
		var actionName = "RoleAction_pageModelsByKeyword.action";
		searchCommon(actionName);
	});

	// rights search link a click
	$("#rights-search").click(function() {
		var actionName = "RightAction_pageModelsByKeyword.action";
		searchCommon(actionName);
	});

	// page event
	$("#page-no-input").change(function() {
		var $value = $(this).val().trim();
		// 1. 校验 val 是否为数字 1, 2, 而不是 a12, b
		var flag = false;
		var reg = /^\d+$/g;
		var pageNo = 1;
		var maxPageNo = $("#total-page-number").val().trim();
		if (reg.test($value)) {
			pageNo = parseInt($value);
			// 2. 校验 val 在一个合法的范围内： 1-totalPageNumber
			if (pageNo >= 1 && pageNo <= parseInt("" + maxPageNo)) {
				flag = true;
			}
		}
		if (!flag) {
			$(this).val("");
			return;
		}
	});

	// add staff
	$("#add-staff").click(function() {
		var actionName = "StaffAction_toAddStaffPage.action";
		formSubmit(actionName);
	});

	// find all staffs
	$("#find-staffs").click(function() {
		var actionName = "StaffAction_pageModels.action";
		formSubmit(actionName);
	});

	// to change password page
	$("#change-pass").click(function() {
		var actionName = "StaffAction_toChangePassPage.action";
		formSubmit(actionName);
	});

	// to my info page
	$("#my-info").click(function() {
		var actionName = "StaffAction_toMyInfoPage.action";
		formSubmit(actionName);
	});

	// the dept's name select change event
	$("#deptNameSelect").change(function() {
		var $value = $(this).children('option:selected').val();
		// alert("option value = " + $value);
		$("#staff-deptName").val($value);

		// alert("select value = " + $("#staff-deptName").val());
	});

	// find all depts
	$("#find-depts").click(function() {
		var actionName = "DepartmentAction_pageModels.action";
		formSubmit(actionName);
	});

	// add dept
	$("#add-dept").click(function() {
		var actionName = "DepartmentAction_toAddDeptPage.action";
		formSubmit(actionName);
	});

	// add role
	$("#add-role").click(function() {
		var actionName = "RoleAction_toAddRolePage.action";
		formSubmit(actionName);
	});

	// find all roles
	$("#find-roles").click(function() {
		var actionName = "RoleAction_pageModels.action";
		formSubmit(actionName);
	});

	// find all rights
	$("#find-rights").click(function() {
		var actionName = "RightAction_pageModels.action";
		formSubmit(actionName);
	});

	// delete right page
	$("#delete-right-page").click(function() {
		var actionName = "RightAction_toDeleteRightPage.action";
		formSubmit(actionName);
	});

	// find logs
	$("#find-log-href")
			.click(
					function() {
						var $value = $("#month-id").val().trim();

						// is number
						var reg = /^\d+$/g;
						if (reg.test($value)) {
							var actionName = "LogAction_findNearestLogs.action?monthId="
									+ parseInt($value);
							formSubmit(actionName);
						} else {
							$("#month-id").val("");
						}
					});

	// select option
	$("#deptNameSelect").change(function() {
		var $value = $(this).children('option:selected').val().trim();
		// alert("value = " + $value);

		$("#staff-deptName").val($value);
		// alert($("#staff-deptName").val());
	});
});