<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.co.icanman.service.BoardService"%>
<%@page import="kr.co.icanman.vo.BoardVo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardUpdate</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
table, th {
	text-align: center;
}
</style>
<%
	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
%>
</head>
<body>
	<h1>게시물 수정</h1>
	<form action="boardController.jsp" method="post">
		<input type="hidden" name="command" value="boardUpdate" />
		<input type="hidden" name="boardNo" value="<%=boardNo %>" />
		<table class="table">
			<thead>
				<tr>
					<th>닉네임</th>
					<th>작성일자</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><h5>제목</h5></td>
					<td colspan="2"><input type="text" class="form-control" name="boardtitle"/></td>
				</tr>
				<tr>
					<td colspan="3"><textarea class="form-control" cols="40" rows="20" ></textarea></td>
				</tr>
				<tr>
				
					<td colspan="3"><input class="btn btn-default" type="submit"
						value="수정" /> <input class="btn btn-default" type="button"
						value="취소" onclick="history.back();" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>