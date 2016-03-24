<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改角色信息页面</title>
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
						class="fs-14 fc_grey">修改角色信息</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="find-roles"> <i class="icon_add"></i>已有角色
					</a><a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-role" style="margin-left: 100px;"> <i class="icon_add"></i>添加角色
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="角色信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="roles-search">&nbsp;</a>
					</label>
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
					<s:form action="RoleAction_saveOrUpdateRole" namespace="/"
						method="post">
						<table class="table-class">
							<tr>
								<th>角色编号:</th>
								<td><input type="text" name="id" value="${id }"
									id="role-id" /></td>
							</tr>
							<tr>
								<th>角色名称:</th>
								<td><input type="text" name="roleName" value="${roleName }"
									id="role-name" /></td>
							</tr>
							<tr>
								<th>角色属性值:</th>
								<td><input type="text" name="roleValue"
									value="${roleValue }" id="role-value" /></td>
							</tr>
							<tr>
								<th>角色描述:</th>
								<td><input type="text" name="roleDesc" value="${roleDesc }"
									id="role-desc" /></td>
							</tr>
							<tr>
								<td colspan="2" style="background-color: rgb(229, 226, 231);"><input
									type="submit" value="Submit" /></td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>