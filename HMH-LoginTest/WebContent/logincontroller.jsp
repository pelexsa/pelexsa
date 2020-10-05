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
				session.setAttribute("message", "아이디 비밀번호를 확인해주세요 5회 이상 실패시 제한됩니다.");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else if (logincheck.equals("ban")) {
				session.setAttribute("message", "5회 이상 실패로 제한되었습니다.");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else {
				session.setAttribute("message", "관리자에게 문의 부탁드립니다.");
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