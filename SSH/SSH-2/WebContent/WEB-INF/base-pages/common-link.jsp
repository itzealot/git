<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- left div-->
	<div class="main_left">
		<s:hidden name="currentPage" id="currentPage"></s:hidden>
		<ul>
			<li id="staff-manage"><s:a namespace="/"
					action="StaffAction_pageModels">员工管理</s:a></li>
			<!-- 
			<li id="webapp-manage"><s:a namespace="/" action="">网站管理</s:a></li>
			<li id="user-manage"><s:a namespace="/" action="">用户管理</s:a></li>
			 -->
			<li id="dept-manage"><s:a namespace="/"
					action="DepartmentAction_pageModels">部门管理</s:a></li>
			<li id="role-manage"><s:a namespace="/"
					action="RoleAction_pageModels">角色管理</s:a></li>
			<li id="right-manage"><s:a namespace="/"
					action="RightAction_pageModels">权限管理</s:a></li>
			<li id="log-manage"><s:a namespace="/"
					action="LogAction_findNearestLogs">日志管理</s:a></li>
		</ul>
	</div>

	<div style="display: none;">
		<s:form action="" method="post" id="search-form" theme="simple">
			<input type="hidden" name="keyword" id="keyword-input" />
			<s:submit value="Submit"></s:submit>
		</s:form>
	</div>
</body>
</html>