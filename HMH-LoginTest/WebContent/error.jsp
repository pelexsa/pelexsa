<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<h1>에러 페이지</h1>
	
	<div>요청 처리 과정에서 에러가 발생하였습니다.<br>
	빠른 시간 내에 문제를 해결하도록 하겠습니다.</div>
	
	<br>
	<br>

	<div>에러 타입 : <%= exception.getClass().getName() %></div>
	<div>에러 메세지 : <%= exception.getMessage() %></div>
</body>
</html>