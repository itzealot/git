<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色授权页面</title>
<!-- s:optiontransferselect标签需要引用 -->
<s:head />
<style type="text/css">
#role-change-title {
	font-size: 17px;
}

#role-change-div {
	font-size: 17px;
}

#form_submit {
	font-size: 17px;
	width: 200px;
	margin-right: 130px;
}

.add-role-link {
	text-decoration: none;
	font-size: 17px;
	float: right;
	margin-right: 20px;
}

td select option {
	font-size: 16px;
}
</style>
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
						class="fs-14 fc_grey">角色授权</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-role"> <i class="icon_add"></i>添加角色
					</a> <a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-staff" style="margin-left: 100px;"> <i
						class="icon_add"></i>添加员工
					</a>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<div style="font-size: 17px;">
					当前角色名称：
					<s:property value="roleName" />
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<div align="center">
					<div align="center" id="role-change-title">角色授予权限</div>
					<div align="center" id="role-change-div">
						<s:form action="RoleAction_roleAddRights" namespace="/"
							method="post">
							<s:hidden name="id"></s:hidden>
							<s:optiontransferselect list="ownRights" name="leftRights"
								leftTitle="角色已授予权限" listKey="id" listValue="rightName"
								multiple="true" headerKey="headerKey"
								headerValue="-----请选择权限-----" emptyOption="false"
								allowUpDownOnLeft="false" cssStyle="width:300px;height:400px;"
								rightTitle="角色未授予权限" doubleList="noRights" doubleListKey="id"
								doubleListValue="rightName" doubleName="rightRights"
								doubleHeaderKey="doubleHeaderKey"
								doubleHeaderValue="-----请选择权限-----" doubleEmptyOption="false"
								doubleMultiple="true" allowUpDownOnRight="false"
								doubleCssStyle="width:300px;height:400px;">
							</s:optiontransferselect>
							<s:submit value="授 予 权 限" id="form_submit"></s:submit>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>