<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询所有员工页面</title>
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
						class="fs-14 fc_grey">查询所有员工</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-staff"> <i class="icon_add"></i>新增员工
					</a> <a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="my-info" style="margin-left: 100px;"> <i class="icon_add"></i>个人信息
					</a> <a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="change-pass" style="margin-left: 100px;"> <i
						class="icon_add"></i>修改密码
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="员工信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="staffs-search">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<table class="table mt_20 table_customer">
					<tr>
						<td colspan="9" style="font-size: 18px;">共有<s:property
								value="#request['page'].totalItemNumber" />个员工; &nbsp; &nbsp;
							本页有<s:property value="#request['page'].list.size()" />个员工
						</td>
					</tr>

					<tr>
						<th width="10%">员工编号</th>
						<th width="10%">员工名称</th>
						<th width="10%">用户名</th>
						<th width="10%">部门编号</th>

						<!-- 
						<th width="15%">部门名称</th>
						 -->

						<th width="10%">备注信息</th>
						<th width="10%">编辑</th>
						<th width="10%">删除</th>
						<th width="10%">授予角色</th>
					</tr>

					<!-- iterator all staff -->
					<s:if test="#request['page'].list.isEmpty() != true">
						<s:iterator value="#request['page'].list" id="staff" status="rt">
							<tr>
								<td class="table_input width_125"><s:property
										value="#staff.id" /></td>
								<td class="table_input width_125"><s:property
										value="#staff.name" /></td>
								<td class="table_input width_125"><s:property
										value="#staff.username" /></td>

								<!-- list departments information -->
								<s:set value="#staff.dept" var="dept" id="dept"></s:set>
								<td class="table_input width_125"><s:property
										value="#dept.id" /></td>

								<!-- 
								<td class="table_input width_125"><s:property
										value="#dept.deptName" /></td>
								-->

								<td class="table_input width_125"><s:property
										value="#staff.remark" /></td>

								<td class="modify fs-14"><a
									href="${pageContext.request.contextPath }/StaffAction_toEditStaffPage?id=${staff.id}"
									style="text-decoration: none;">编辑</a></td>
								<s:if test="#staff.username != 'admin'">
									<td class="modify fs-14"><a
										href="${pageContext.request.contextPath }/StaffAction_deleteStaff?id=${staff.id}"
										style="text-decoration: none;">删除</a></td>
								</s:if>
								<s:else>
									<td>删除</td>
								</s:else>
								<td class="modify fs-14"><a
									href="${pageContext.request.contextPath }/StaffAction_toStaffAddRolesPage?id=${staff.id}"
									style="text-decoration: none;">授予角色</a></td>
							</tr>
						</s:iterator>
					</s:if>
				</table>
				<s:include value="/WEB-INF/base-pages/page.jsp"></s:include>
			</div>
		</div>
	</div>
</body>
</html>