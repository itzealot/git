<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改权限信息页面</title>
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
					<span class="pl_15 fs-18">权限管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">修改权限信息</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="find-rights"> <i class="icon_add"></i>已有权限
					</a> <a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="delete-right" style="margin-left: 100px;"> <i
						class="icon_add"></i>删除权限
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="权限信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="rights-search">&nbsp;</a>
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
					<s:form action="RightAction_saveOrUpdateRight" namespace="/"
						method="post" theme="simple">
						<table class="table-class">
							<tr>
								<th>编号:</th>
								<td><s:textfield name="id" readonly="true"></s:textfield></td>
							</tr>
							<tr>
								<th>权限名称:</th>
								<td><s:textfield name="rightName"></s:textfield></td>
							</tr>
							<tr>
								<th>权限地址:</th>
								<td><s:textfield name="rightUrl" readonly="true"></s:textfield></td>
							</tr>
							<tr>
								<th>权限描述:</th>
								<td><s:textfield name="rightDesc"></s:textfield></td>
							</tr>
							<tr>
								<th>权限码:</th>
								<td><s:textfield name="rightCode" readonly="true"></s:textfield></td>
							</tr>
							<tr>
								<th>权限位:</th>
								<td><s:textfield name="rightPos" readonly="true"></s:textfield></td>
							</tr>
							<tr>
								<th>公有:</th>
								<td><s:textfield name="common"></s:textfield></td>
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