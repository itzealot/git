<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page</title>
</head>
<body>

	<!-- page log -->
	<div id="div-page-link">

		<!-- 首页 -->
		<s:if test="!(#request['page'].isFirst())">
			<a
				href="${pageContext.request.contextPath }/LogAction_findNearestLogs?monthId=${monthId}&pageNo=1&pageSize=5">首页</a>
		</s:if>
		<s:else>
			首页
		</s:else>

		<!-- 上一页 -->
		<s:if test="#request['page'].isHasPrev()">
						&nbsp; <a
				href="${pageContext.request.contextPath }/LogAction_findNearestLogs?monthId=${monthId}&pageNo=<s:property value="#request['page'].pageNo-1" />&pageSize=5">上一页</a>
		</s:if>
		<s:else>
			&nbsp;上一页
		</s:else>

		<!-- 下一页 -->
		<s:if test="#request['page'].isHasNext()">
						&nbsp; <a
				href="${pageContext.request.contextPath }/LogAction_findNearestLogs?monthId=${monthId}&pageNo=<s:property value="#request['page'].pageNo+1" />&pageSize=5">下一页&nbsp;</a>
		</s:if>
		<s:else>
			&nbsp;下一页
		</s:else>

		<!-- 尾页 -->
		<s:if test="!(#request['page'].isLast())">
						&nbsp;<a
				href="${pageContext.request.contextPath }/LogAction_findNearestLogs?monthId=${monthId}&pageNo=<s:property value="#request['page'].totalPageNumber" />&pageSize=5">尾页&nbsp;</a>
		</s:if>
		<s:else>
			&nbsp;尾页&nbsp;
		</s:else>

		共有
		<s:property value="#request['page'].totalPageNumber" />
		<input type="hidden" id="total-page-number"
			value="<s:property value="#request['page'].totalPageNumber" />" /> 页
		&nbsp;

		<s:form namespace="/" action="LogAction_findNearestLogs"
			theme="simple" cssStyle="float:right;margin-right:200px;">
			<s:textfield name="pageNo" id="page-no-input"></s:textfield>
			<s:submit value="Go" id="div-page-link-submit"></s:submit>
		</s:form>
	</div>
</body>
</html>