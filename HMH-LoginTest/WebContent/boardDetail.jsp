<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="kr.co.icanman.service.BoardService"%>
<%@page import="kr.co.icanman.vo.BoardVo"%>
<%@page import="kr.co.icanman.vo.CommentVo"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
table, th {
	text-align: center;
}

#boardDetailtitle {
	margin-top: 70px;
	margin-bottom: 85px;
	text-align: center;
}

#comment {
	padding-left: 180px;
	text-align: left;
	height: 100px;
}

#userCommnet {
	text-align: left;
}
</style>
<%
	int boardNo = (Integer) request.getAttribute("boardNo");
BoardVo boardDetailInfo = (BoardVo) request.getAttribute("boardDetailInfo");
List<CommentVo> userCommentList = (List<CommentVo>) request.getAttribute("userCommentList");
%>
</head>
<body>
	<h1 id="boardDetailtitle">게시판 디테일 보기</h1>
	<table class="table">
		<thead>
			<tr>
				<th><%=boardDetailInfo.getUserID()%></th>
				<th><%=boardDetailInfo.getBoardDate()%></th>
				<th>조회수 : <%=boardDetailInfo.getBoardHit()%></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>제목</td>
				<td class="text-left" colspan="2"><%=boardDetailInfo.getBoardTitle()%></td>
			</tr>
			<tr>
				<td colspan="3"><textarea class="form-control" cols="40"
						rows="20" readonly="readonly"><%=boardDetailInfo.getBoardContent()%></textarea></td>
			</tr>
			<tr>
				<td><input class="btn btn-default" type="button" value="목록"
					onclick="location.href='boardController.jsp?command=boardList'" /></td>
				<td><input class="btn btn-default" type="button" value="수정"
					onclick="location.href='boardController.jsp?command=boardUpdateForm&boardNo=<%=boardNo%>'" /></td>
				<td><input class="btn btn-default" type="button" value="삭제"
					onclick="location.href='boardController.jsp?command=boardDelete&boardNo=<%=boardNo%>'" /></td>
				<td><input class="btn btn-default" type="button" value="답글"
					onclick="location.href='boardReplyInsert.jsp?command=boardReplyInsert&boardNo=<%=boardNo%>'" /></td>
			</tr>
		</tbody>
	</table>

	<div></div>
</body>
</html>