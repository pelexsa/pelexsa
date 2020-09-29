<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="error.jsp"%>
<%@ page import="kr.co.icanman.vo.LoginVo" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>로그인 페이지</title>
<style type="text/css">
input, textarea, select {
	text-align: center;
	width: 100%; 
}

h1 {
	text-align: center;
}
.container{
	
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div class="container">
	<div>
		<h1>아이캔 매니지먼트 로그인</h1>
	</div>
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="loginCheck" />
		<div class="form-group">
			<label for="exampleInputEmail1">아이디</label> <input type="text"
				class="form-control" id="exampleInputEmail1" name="userID"
				placeholder="아이디를 입력하세요">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">암호</label> <input type="password"
				class="form-control" id="exampleInputPassword1" name="userPassword"
				placeholder="암호">
		</div>
		<input class="btn btn-default" type="submit" value="로그인" />
	</form>
	</div>
</body>
</html>