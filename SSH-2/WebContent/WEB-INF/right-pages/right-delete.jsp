<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除权限页面</title>
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
						class="fs-14 fc_grey">删除权限</span>
				</div>


				<!-- search info -->
				<div class="btn_seach">
					<a href="javascript:;" class="fl add_btn fs-14 fc_white"
						id="find-rights"> <i class="icon_add"></i>已有权限
					</a> <label class="fr seach"> <input class="fl seach_input"
						placeholder="权限信息" type="text" id="keyword"> <!-- search click -->
						<a class="seach_btn fl" href="javascript:;" id="rights-search">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<table class="table mt_20 table_customer">

					<tr>
						<td colspan="7" style="font-size: 18px;">共有<s:property
								value="#request['page'].totalItemNumber" />个权限; &nbsp; &nbsp;
							本页有<s:property value="#request['page'].list.size()" />个权限
						</td>
					</tr>
					<tr>
						<th width="5%">编号</th>
						<th width="20%">权限名称</th>
						<th width="25%">权限地址</th>
						<th width="10%">权限描述</th>
						<th width="5%">编辑</th>
						<th width="5%">删除</th>
					</tr>

					<s:if test="#request['page'].list.isEmpty() != true">
						<s:iterator value="#request['page'].list" id="right" status="rt">
							<tr>
								<td class="table_input width_125"><s:property
										value="#right.id" /></td>
								<td class="table_input width_125"><s:property
										value="#right.rightName" /></td>
								<td class="table_input width_125"><s:property
										value="#right.rightUrl" /></td>
								<td class="table_input width_125"><s:property
										value="#right.rightDesc" /></td>
								<td class="table_input width_125"><a
									href="${pageContext.request.contextPath }/RightAction_editRight?id=${right.id}"
									style="text-decoration: none;">编辑</a></td>
								<td class="table_input width_125"><a
									href="${pageContext.request.contextPath }/RightAction_deleteRight?id=${right.id}"
									style="text-decoration: none;">删除</a></td>
							</tr>
						</s:iterator>
					</s:if>
				</table>
				<div id="div-page-link">
					<s:if test="!(#request['page'].isFirst())">
						<a
							href="${pageContext.request.contextPath }/RightAction_toDeleteRightPage?pageNo=1">首页</a>
					</s:if>
					<s:else>
						首页
					</s:else>
					<s:if test="#request['page'].isHasPrev()">
						&nbsp; <a
							href="${pageContext.request.contextPath }/RightAction_toDeleteRightPage?pageNo=<s:property value="#request['page'].pageNo-1" />">上一页</a>
					</s:if>
					<s:else>
						&nbsp;上一页
					</s:else>

					<s:if test="#request['page'].isHasNext()">
						&nbsp; <a
							href="${pageContext.request.contextPath }/RightAction_toDeleteRightPage?pageNo=<s:property value="#request['page'].pageNo+1" />">下一页&nbsp;</a>
					</s:if>
					<s:else>
						&nbsp;下一页
					</s:else>

					<s:if test="!(#request['page'].isLast())">
						&nbsp;<a
							href="${pageContext.request.contextPath }/RightAction_toDeleteRightPage?pageNo=<s:property value="#request['page'].totalPageNumber" />">尾页&nbsp;</a>
					</s:if>
					<s:else>
						&nbsp;尾页&nbsp;
					</s:else>
					共有
					<s:property value="#request['page'].totalPageNumber" />
					<input type="hidden" id="total-page-number"
						value="<s:property value="#request['page'].totalPageNumber" />" />
					页 &nbsp;
					<s:form namespace="/" action="RightAction_pageModels"
						theme="simple" cssStyle="float:right;margin-right:200px;">
						<s:textfield name="pageNo" id="page-no-input"></s:textfield>
						<s:submit value="Go" id="div-page-link-submit"></s:submit>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>