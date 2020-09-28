<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.icanman.vo.LoginVo"%>
<%@page import="kr.co.icanman.dao.LoginDao"%>
<%@page import="kr.co.icanman.service.LoginService"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
		// 커맨드로 파라미터를 받아서 요청을 구분처리
		String command = request.getParameter("command");

		if (command.equals("loginCheck")) {
			String userID = request.getParameter("userID");
			String userPassword = request.getParameter("userPassword");
			LoginService loginService = new LoginService();

			String logincheck = loginService.viewChange(userID, userPassword);

			// 로그인 체크 부분
			if (logincheck.equals("success")) {
				// 로그인 성공시 테이블 회원정보 세션에 담기
				LoginVo loginVo = loginService.findUserinfo(userID,userPassword);
				session.setAttribute("userInfo", loginVo);
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command="+ "boardList");
			} else if (logincheck.equals("false")) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else if (logincheck.equals("ban")) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
		}

	} catch (Exception e) {
		RequestDispatcher forwardDisPatcher = request.getRequestDispatcher("error.jsp");
		forwardDisPatcher.forward(request, response);
	}
	%>
</body>
</html>