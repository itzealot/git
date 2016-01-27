<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询所有日志</title>
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
					<span class="pl_15 fs-18">日志管理</span> <i
						class="font_icon_01 ml_5 mr_5 fs-14">></i> <span
						class="fs-14 fc_grey">查询日志信息</span>
				</div>

				<!-- search info -->
				<div class="btn_seach">
					<label class="fr seach"> <input class="fl seach_input"
						id="month-id" name="monthId" placeholder="最近月份" type="text">
						<a id="find-log-href" class="seach_btn fl">&nbsp;</a>
					</label>
				</div>

				<!-- a empty div -->
				<div class="clear"></div>

				<!-- all info table -->
				<table class="table mt_20 table_customer">
					<tr>
						<td colspan="6" style="font-size: 18px;">共有<s:property
								value="#request['page'].totalItemNumber" />条日志; &nbsp; &nbsp;
							本页有<s:property value="#request['page'].list.size()" />条日志
						</td>
					</tr>

					<tr>
						<th>操作人</th>
						<th>操作名称</th>
						<th>操作参数</th>
						<th>操作结果</th>
						<th>结果信息</th>
						<th>操作时间</th>
					</tr>

					<!-- all logs display -->
					<s:if test="!allLogs.isEmpty()">
						<s:iterator value="#request['page'].list" id="log" status="lg">
							<tr>
								<td class="table_input width_125"><s:property
										value="#log.operator" /></td>
								<td class="table_input width_125"><s:property
										value="#log.operName" /></td>
								<td class="table_input width_125"><s:property
										value="#log.operParams" /></td>
								<td class="table_input width_125"><s:property
										value="#log.operResult" /></td>
								<td class="table_input width_125"><s:property
										value="#log.resultMsg" /></td>
								<td class="table_input width_125"><s:date
										name="#log.operTime" format="yyyy-MM-dd hh:mm:ss" /></td>
							</tr>
						</s:iterator>
					</s:if>
				</table>
				<s:include value="/WEB-INF/base-pages/page-log.jsp"></s:include>
			</div>
		</div>
	</div>
</body>
</html>