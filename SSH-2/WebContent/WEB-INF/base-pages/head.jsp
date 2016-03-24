<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/reset.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/global.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/page.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/table.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common-link.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/page.js"></script>
</head>
<body>
	<header class="top">
	<div class="top_box">
		<span class="fl fs-24 fc_purple_01 pl_20 fc_white_01 mt_35">德成保洁公司管理系统</span>
		<i class="fr mt_70 fc_white_01 fs-16"> 管理员：<s:property
				value="#session['staff'].name" /> &nbsp;&nbsp; <s:a namespace="/"
				action="LogoutAction_doLogout" cssClass="mt_70 fc_white_01">注销</s:a>
		</i>
	</div>
	</header>

	<div style="display: none">
		<input type="hidden" value="${pageContext.request.contextPath }"
			id="realPath" />

		<!-- To using  -->
		<s:form action="" namespace="/" id="common-form" theme="simple">
			<s:hidden name="currentPage"></s:hidden>
			<s:hidden name="pageNo"></s:hidden>
			<s:hidden name="pageSize"></s:hidden>
			<s:hidden></s:hidden>
		</s:form>
	</div>
</body>
</html>