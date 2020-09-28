<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.co.icanman.vo.LoginVo"%>
<%@ page import="kr.co.icanman.vo.BoardVo"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<style>
div{
	text-align: center;
}
#loginSuccess{
	margin-bottom: 120px;
}
td {
	padding: 20px 0px !important;
}

table, th, h1 {
	text-align: center;
}

h1 {
	margin-top: 50px;
	margin-bottom: 70px;
}

th {
	width: 50px;
}

.btnClass {
	width: 10%;
	margin: 0 auto;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<%
	List<BoardVo> list = (List<BoardVo>) request.getAttribute("list");
	LoginVo uesrInfo = (LoginVo)session.getAttribute("userInfo");


	if(uesrInfo.getUserName() == null){
		response.sendRedirect(request.getContextPath()+"/error.jsp");
	}
%>
</head>
<body>
	<div><h1><%=uesrInfo.getUserName()%>님 환영합니다</h1></div>
	<div id="loginSuccess"><h2>접속일자  : <%=uesrInfo.getLoginSuccessdate()%></h2></div>
	
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>


		<tbody>
			<%
				for (BoardVo boardList : list) {
			%>
			<tr>
				<td><%=boardList.getRownum() %></td>
				<td><%=boardList.getUserID()%></td>
				<td><a href=boardController.jsp?command=boardDetail&boardNo=<%=boardList.getBoardNo()%>><%=boardList.getBoardTitle()%></a></td>
				<td><%=boardList.getBoardDate()%></td>
				<td><%=boardList.getBoardHit()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<div class="btnClass">
		<input class="btn btn-default" type="button" value="글쓰기" onclick="location.href='boardInsert.jsp';" />
	</div>
</body>
</html>