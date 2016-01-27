<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- s:optiontransferselect must -->
<s:head />
<style type="text/css">
.div-class-staff {
	font-size: 17px;
}

.div-class-staff form {
	font-size: 17px;
}

#form_submit {
	font-size: 16px;
	width: 200px;
	margin-right: 130px;
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
					<span class="pl_15 fs-18">员工管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">授予角色</span>
				</div>

				<!-- search info -->
				<div align="center" class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-staff"> <i class="icon_add"></i>新增员工
					</a> <a href="javascript:;" class="fl add_btn fs-14 fc_white"
						style="margin-left: 50px;" id="add-role"> <i class="icon_add"></i>新增角色
					</a><label class="fr seach"> <input class="fl seach_input"
						placeholder="员工信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="staffs-search">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<div align="center" class="div-class-staff">管理员授予角色</div>

				<!-- staff add role form start -->
				<div align="center" class="div-class-staff">
					<s:form action="StaffAction_staffAddRoles" namespace="/"
						method="post">
						<s:hidden name="id"></s:hidden>
						<s:optiontransferselect list="ownRoles" name="leftRoles"
							leftTitle="用户已授予角色" listKey="id" listValue="roleName"
							multiple="true" headerKey="headerKey" headerValue="---请选择用户角色---"
							emptyOption="false" allowUpDownOnLeft="false"
							cssStyle="width:200px;height:300px;" rightTitle="用户未授予角色"
							doubleList="noRoles" doubleListKey="id"
							doubleListValue="roleName" doubleName="rightRoles"
							doubleHeaderKey="doubleHeaderKey"
							doubleHeaderValue="---请选择用户角色---" doubleEmptyOption="false"
							doubleMultiple="true" allowUpDownOnRight="false"
							doubleCssStyle="width:200px;height:300px;">
						</s:optiontransferselect>
						<s:submit value="授 予 角 色" id="form_submit"></s:submit>
					</s:form>
				</div>
				<!-- staff add role form end -->

			</div>
		</div>
	</div>
</body>
</html>