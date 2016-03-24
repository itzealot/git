<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询所有部门</title>
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
						class="fs-14 fc_grey">查询所有部门</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="add-dept"> <i class="icon_add"></i>新增部门
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="部门信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="depts-search">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<div align="center">
					<!-- Error msg -->
					<div id="error-msg">
						<s:actionerror />
					</div>
				</div>


				<!-- all info table -->
				<table class="table mt_20 table_customer">
					<tr>
						<td colspan="9" style="font-size: 18px;">共有<s:property
								value="#request['page'].totalItemNumber" />个部门; &nbsp; &nbsp;
							本页有<s:property value="#request['page'].list.size()" />个部门
						</td>
					</tr>
					<tr>
						<th width="10">部门编号</th>
						<th width="20">部门名称</th>
						<th width="20">部门备注</th>
						<th width="10">编辑</th>
						<th width="10">删除</th>
					</tr>

					<s:if test="#request['page'].list.isEmpty() != true">
						<!-- iterator all depts -->
						<s:iterator value="#request['page'].list" id="dept" status="rt">
							<tr>
								<td class="table_input width_125"><s:property
										value="#dept.id" /></td>
								<td class="table_input width_125"><s:property
										value="#dept.deptName" /></td>
								<td class="table_input width_125"><s:property
										value="#dept.remark" /></td>
								<td class="table_input width_125"><a
									href="${pageContext.request.contextPath }/DepartmentAction_toEditDeptPage?id=${dept.id}"
									style="text-decoration: none;">编辑</a></td>
								<td class="table_input width_125"><a
									href="${pageContext.request.contextPath }/DepartmentAction_deleteDept?id=${dept.id}"
									style="text-decoration: none;">删除</a></td>
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