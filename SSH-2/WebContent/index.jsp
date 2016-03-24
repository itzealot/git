<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>德成保洁公司管理员登入</title>
<link href="${pageContext.request.contextPath }/css/reset.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/global.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>
<script type="text/javascript">
	function setUsernameAttr() {
		var $value = $("#username-input").val().trim();
		if ($value == "") {
			$("#error-msg").text("Username is must!");
			return false;
		} else {
			$("#error-msg").text("");
			return true;
		}
	};

	function setPasswordAttr() {
		if ($("#password-input").val() == "") {
			$("#error-msg").text("Password is must!");
			return false;
		} else {
			$("#error-msg").text("");
			return true;
		}
	};
	$(function() {
		//form submit event
		$("#form-submit").click(function() {

			var flag = setUsernameAttr();
			if (!flag) {
				return;
			}

			flag = setPasswordAttr();
			if (!flag) {
				return;
			}

			$("#login-form").submit();
		});
	});
</script>
<body>
	<header class="top"></header>
	<div class="login_box">
		<div class="login_box_top">
			<span class="fs-24 fc_purple_01 pl_20">德成保洁公司管理员登入</span>
		</div>

		<!-- manager login form -->
		<form id="login-form"
			action="${pageContext.request.contextPath }/LoginAction_doLogin"
			method="post">
			<span class="prompt ml_69 fs-14" id="error-msg"> <s:fielderror>
					<s:param>username-error</s:param>
				</s:fielderror>
			</span> <label class="login_user ml_69"> <i class="icon_01 fl"></i>
				<input id="username-input" class="fl fs-14" name="username"
				type="text" />
			</label> <label class="login_password ml_69 fl"> <i
				class="icon_02 fl"></i> <input id="password-input" class="fl fs-14"
				name="password" type="password" /></label> <a
				class=" fl login_btn fs-16 dis_block al_center ml_69 mt_30"
				id="form-submit">登录</a>
		</form>
	</div>
</body>
</html>