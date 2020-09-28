<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.icanman.service.BoardService"%>
<%@page import="kr.co.icanman.vo.BoardVo"%>
<%@page import="kr.co.icanman.service.BoardService"%>
<%@page import="kr.co.icanman.vo.LoginVo"%>
<%@page import="kr.co.icanman.vo.CommentVo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
		//인코딩
		request.setCharacterEncoding("utf-8");

		//역할 분담을 위한 파라미터
		String command = request.getParameter("command");
		

		if (command.equals("boardList")) {
			// 게시판 출력
			BoardService boardService = new BoardService();
			List<BoardVo> list = new ArrayList<BoardVo>();
			list = boardService.boardListAll();

			// forward방식 데이터 전달
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/boardList.jsp");
			request.setAttribute("list", list);
			dispatcher.forward(request, response);
		}else if (command.equals("boardDetail")) {
			// 게시물 하나보기
			LoginVo userInfo = (LoginVo) session.getAttribute("userInfo");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			BoardService boardService = new BoardService();
			BoardVo boardDetailInfo = boardService.boardDetailInfo(userInfo.getUserID(), boardNo);

			// forward방식으로 디테일에 필요한 정보 보내기
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/boardDetail.jsp");
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("boardDetailInfo", boardDetailInfo);
			dispatcher.forward(request, response);
		} else if (command.equals("boardWrite")) {
			// 게시물 작성
			LoginVo userInfo = (LoginVo) session.getAttribute("userInfo");
			BoardService boardService = new BoardService();
			String boardtitle = request.getParameter("boardtitle");
			String boardContent = request.getParameter("boardContent");
			
			//성공시 페이지 전환
			int boardWrite = boardService.boardWrite(boardtitle, boardContent, userInfo.getUserID());
			if (boardWrite > 0) {
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=" + "boardList");
			}
		} else if(command.equals("boardReplyInsert")){
			// 답글 달기
			LoginVo userInfo = (LoginVo) session.getAttribute("userInfo");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			BoardService boardService = new BoardService();
			String boardtitle = request.getParameter("boardtitle");
			String boardContent = request.getParameter("boardContent");
			BoardVo parentVo = boardService.boardDetailInfo(userInfo.getUserID(),boardNo);
			
			//성공시 페이지 전환
			int boardReply = boardService.boardReplyInsert(boardtitle, boardContent, userInfo.getUserID(),parentVo);
			
			if (boardReply > 0) {
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=boardList");
			}
			
		}else if (command.equals("boardDelete")) {
			// 게시물 삭제
			LoginVo userInfo = (LoginVo) session.getAttribute("userInfo");
			BoardService boardService = new BoardService();
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int boardDelete = 0;
			
			// 작성자인지 확인
			String writerCheck = boardService.writerCheck(userInfo.getUserID(), boardNo);

			//작성자 일경우 삭제실행
			if (writerCheck.equals("true")) {
				boardDelete = boardService.boardDelete(boardNo);
				if (boardDelete > 0) {
					response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=" + "boardList");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=" + "boardList");
			}
		} else if (command.equals("boardUpdateForm")) {
			// 작성자 확인 후 , 게시물 수정폼 이동 -> 서비스단으로 변경
			LoginVo userInfo = (LoginVo) session.getAttribute("userInfo");
			BoardService boardService = new BoardService();
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));

			// 작성자 확인
			// 맞을경우 -> 수정폼 , 틀릴경우 -> 리스트
			String writerCheck = boardService.writerCheck(userInfo.getUserID(), boardNo);
			
			if (writerCheck.equals("true")) {
				response.sendRedirect(request.getContextPath() + "/boardUpdate.jsp?boardNo=" + boardNo);
			} else {
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=" + "boardList");
			}

		} else if (command.equals("boardUpdate")) {
			// 게시물 수정
			BoardService boardService = new BoardService();
			String boardtitle = request.getParameter("boardtitle");
			String boardContent = request.getParameter("boardContent");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));

			int boardUpdate = boardService.boardUpdate(boardtitle, boardContent, boardNo);

			if (boardUpdate > 0) {
				response.sendRedirect(request.getContextPath() + "/boardController.jsp?command=" + "boardList");
			}
		} 
	} catch (Exception e) {
		RequestDispatcher forwardDisPatcher = request.getRequestDispatcher("error.jsp");
		forwardDisPatcher.forward(request, response);
	}
	%>

</body>
</html>