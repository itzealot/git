<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询所有角色</title>
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
					<span class="pl_15 fs-18">角色管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">查询所有角色</span>
				</div>


				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-role"> <i class="icon_add"></i>新增角色
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="角色信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="roles-search">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<table class="table mt_20 table_customer">
					<tr>
						<td colspan="7" style="font-size: 18px;">共有<s:property
								value="#request['page'].totalItemNumber" />个角色; &nbsp; &nbsp;
							本页有<s:property value="#request['page'].list.size()" />个角色
						</td>
					</tr>

					<tr>
						<th width="10%">角色编号</th>
						<th width="10%">角色名称</th>
						<th width="10%">角色值</th>
						<th width="10%">角色描述</th>
						<th width="10%">编辑</th>
						<th width="10%">删除</th>
						<th width="10%">授权</th>
					</tr>
					<s:if test="#request['page'].list.isEmpty() != true">
						<!-- iterator -->
						<s:iterator value="#request['page'].list" id="role" status="rt">
							<tr>
								<td class="table_input width_125"><s:property
										value="#role.id" /></td>
								<td class="table_input width_125"><s:property
										value="#role.roleName" /></td>
								<td class="table_input width_125"><s:property
										value="#role.roleValue" /></td>
								<td class="table_input width_125"><s:property
										value="#role.roleDesc" /></td>
								<td class="table_input width_125"><a class="modify fs-14"
									href="${pageContext.request.contextPath }/RoleAction_toEditRolePage?id=${role.id}">编辑</a></td>
								<td class="table_input width_125"><a class="modify fs-14"
									href="${pageContext.request.contextPath }/RoleAction_deleteRole?id=${role.id}">删除</a></td>
								<td class="table_input width_125"><a class="modify fs-14"
									href="${pageContext.request.contextPath }/RoleAction_toRoleAddRightsPage?id=${role.id}">授权</a></td>
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