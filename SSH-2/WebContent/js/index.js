/**
 * To remove or set the attr
 */
function removeOrSet() {
	if (($("#password-input").val() != "")
			&& ($("#username-input").val().trim() != "")) {
		$("#form-submit").removeAttr("disabled");
	} else {
		$("#form-submit").attr("disabled", "true");
	}
}
/**
 * 
 */
function setAttr() {
	$("#form-submit").attr("disabled", "true");
}
function setUsernameAttr() {
	var $value = $("#username-input").val().trim();
	if ($value == "") {
		$("#error-msg").html("Username is must!");
		setAttr();
	} else {
		$("#error-msg").html("");
		removeOrSet();
	}
}
function setPasswordAttr() {
	if ($("#password-input").val() == "") {
		$("#error-msg").html("Password is must!");
		setAttr();
	} else {
		$("#error-msg").html("");
		removeOrSet();
	}
}
$(function() {
	removeOrSet();
	/**
	 * username
	 */
	$("#username-input").blur(function() {
		setUsernameAttr();
	});

	$("#username-input").keyup(function() {
		setUsernameAttr();
	});

	/**
	 * password
	 */
	$("#password-input").blur(function() {
		setPasswordAttr();
	});

	$("#password-input").keyup(function() {
		setPasswordAttr();
	});
});