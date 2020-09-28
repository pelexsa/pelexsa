<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardCreate</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<style type="text/css">
div {
	text-align: center;
	margin-top: 80px;
	margin-bottom: 100px;
}

.inputTag {
	width: 1000px;
	margin: 0 auto;
	margin-top: 40px;
	
}
</style>
<%
	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
%>
</head>
<body>
	<div>
		<h1>게시물 답글 작성</h1>
	</div>
	<form action="boardController.jsp" method="post">
		<input type="hidden" name="command" value="boardReplyInsert" />
		<input type="hidden" name="boardNo" value="<%=boardNo%>">
		<input type="text" class="form-control" name="boardtitle" placeholder="제목">
		<textarea class="form-control" cols="40" rows="20" name="boardContent" placeholder="내용"></textarea>
		<div class="inputTag">
			<input class="btn btn-default" type="submit" value="작성" />
			<input class="btn btn-default" type="button" value="취소" onclick="history.back();" />
		</div>
	</form>
</body>
</html>