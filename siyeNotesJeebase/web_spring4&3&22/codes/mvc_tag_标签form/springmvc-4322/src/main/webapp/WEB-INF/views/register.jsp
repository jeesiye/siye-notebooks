<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${pageContext.request.contextPath }/test">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="test" modelAttribute="user" method="delete">
		<form:input path="username" />
		<form:password path="passwd" />
		<button type="submit">submit</button>
	</form:form>

</body>
</html>