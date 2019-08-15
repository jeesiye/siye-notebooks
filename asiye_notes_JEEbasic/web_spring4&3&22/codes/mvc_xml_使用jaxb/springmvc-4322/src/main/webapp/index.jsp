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
		//注意,要对特殊字符进行转义
		var xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <book><id>1</id><name>从入门到放弃</name><author>某某某</author></book>";
		$('#click').click(function() {
			console.log(1);
			$.ajax({
				url : "${pageContext.request.contextPath}/test",
				async : true,
				dateType : 'xml',
				type : 'post',
				contentType : 'application/xml',
				data : xmlData,
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



