<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改部门信息</title>
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
					<span class="pl_15 fs-18">部门管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">修改部门信息</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="find-depts"> <i class="icon_add"></i>已有部门
					</a><a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-dept" style="margin-left: 100px;"> <i class="icon_add"></i>新增部门
					</a><label class="fr seach"> <input class="fl seach_input"
						placeholder="部门信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="depts-search">&nbsp;</a>
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
					<s:form action="DepartmentAction_saveOrUpdateDept" namespace="/"
						method="post">
						<table class="table-class">
							<tr>
								<th>部门编号:</th>
								<td><input type="text" name="id" value="${id }"
									id="dept-id" readonly="readonly" /></td>
							</tr>
							<tr>
								<th>部门名称:</th>
								<td><input type="hidden" name="deptName"
									value="${deptName }" id="dept-name" /> <select
									id="deptNameSelect">
										<option value="人事部">人事部</option>
										<option value="财务部">财务部</option>
										<option value="销售部">销售部</option>
								</select></td>
							</tr>
							<tr>
								<th>备注信息:</th>
								<td><input type="text" name="remark" value="${remark }" /></td>
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