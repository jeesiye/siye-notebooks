<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="UTF-8">
<script src="https://libs.cdnjs.net/jquery/3.4.1/jquery.min.js"></script>
<script src="https://libs.cdnjs.net/json2/20160511/json2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#click').click(function() {
			console.log(1);
			$.ajax({
				url : "${pageContext.request.contextPath}/test",
				async : true,
				dateType : 'json',
				type : 'post',
				contentType : 'application/json;charset:utf-8',
				data : JSON.stringify({
					id : '14',
					age : 34,
					name : 'jack'
				}),
				success : function(data) {
					console.log(data);
				},
				error : function(data) {
					console.log(data);
				}
			});
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<button id="click">submit</button>
</body>
</html>



