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
	<div id="div-page-link">
		<s:if test="!(#request['page'].isFirst())">
			<a href="javascript:;" id="first-page">首页</a>
		</s:if>
		<s:else>
			首页
		</s:else>

		<s:if test="#request['page'].isHasPrev()">
			&nbsp; <a href="javascript:;" id="pre-page">上一页&nbsp; </a>
		</s:if>
		<s:else>
			&nbsp;上一页&nbsp; 
		</s:else>

		<s:if test="#request['page'].isHasNext()">
				&nbsp; <a href="javascript:;" id="next-page">下一页&nbsp;</a>
		</s:if>
		<s:else>
			&nbsp;下一页&nbsp; 
		</s:else>

		<s:if test="!(#request['page'].isLast())">
			&nbsp;<a href="javascript:;" id="last-page">尾页&nbsp;</a>
		</s:if>
		<s:else>
			&nbsp;尾页&nbsp;
		</s:else>

		共有
		<s:property value="#request['page'].totalPageNumber" />
		<input type="hidden" id="total-page-number"
			value="<s:property value="#request['page'].totalPageNumber" />" />
		页&nbsp;

		<!-- Input the pageNo Action -->
		<s:form namespace="/" action="" theme="simple"
			cssStyle="float:right;margin-right:160px;" id="page-form">
			<s:textfield name="pageNo" id="page-no-input"></s:textfield>
			<s:hidden name="keyword"></s:hidden>
			<s:submit value="Go" id="div-page-link-submit"></s:submit>
		</s:form>
	</div>
</body>
</html>