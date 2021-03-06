<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页面</title>
</head>
<body>
	<s:include value="/WEB-INF/base-pages/head.jsp"></s:include>

	<!-- main box -->
	<div class="main_box">

		<!-- main background -->
		<div class="main_bg">

			<!-- left div-->
			<s:include value="/WEB-INF/base-pages/common-link.jsp"></s:include>

			<!-- right div -->
			<div class="main_right">

				<!-- current page -->
				<div class="position mb_20">
					<span class="pl_15 fs-18">员工管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">查看个人信息</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="find-staffs"> <i class="icon_add"></i>已有员工
					</a><a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="change-pass" style="margin-left: 100px;"> <i
						class="icon_add"></i>修改密码
					</a>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<!-- center form -->
				<div align="center">

					<!-- Error msg -->
					<div id="error-msg">
						<s:actionerror />
					</div>

					<!-- add staff form -->
					<s:form action="StaffAction_updateStaff" namespace="/"
						method="post">
						<table class="table-class">
							<tr>
								<th>员工编号:</th>
								<td><input type="text" name="id" value="${staff.id }"
									id="staff-id" readonly="readonly" /></td>
							</tr>
							<tr>
								<th>员工名称:</th>
								<td><input type="text" name="name" value="${staff.name }"
									id="staff-name" /></td>
							</tr>
							<tr>
								<th>用户名:</th>
								<td><input type="text" name="username"
									value="${staff.username }" readonly="readonly" /></td>
							</tr>
							<tr>
								<th>部门名称:</th>
								<td><input type="text" name="deptName"
									value="${dept.deptName }" readonly="readonly"
									id="staff-deptName" /></td>
							</tr>
							<tr>
								<th>备注信息:</th>
								<td><input type="text" name="remark"
									value="${staff.remark }" /></td>
							</tr>
							<tr>
								<td colspan="2" style="background-color: rgb(229, 226, 231);"><input
									type="submit" value="Change" /></td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>