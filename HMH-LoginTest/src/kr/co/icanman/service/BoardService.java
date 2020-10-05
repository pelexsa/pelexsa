package kr.co.icanman.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import kr.co.icanman.dao.BoardDao;
import kr.co.icanman.vo.BoardVo;
import kr.co.icanman.vo.CommentVo;

public class BoardService {

	protected Logger logger = Logger.getRootLogger();

	// 모든 값 가져오기
	public List<BoardVo> boardListAll() throws Exception {
		Connection con = null;
		BoardDao dao = null;
		List<BoardVo> boardListAll = null;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardListAll = dao.boardListAll(con);
		} catch (Exception e) {
			logger.error("boardListAllerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}

		return boardListAll;
	}

	// boardDetail 뿌려줄 데이터 받는 메소드
	public BoardVo boardDetailInfo(String userID, int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		BoardVo boardDetailInfo = null;
		String writerCheck = null;

		try {
			con = getConnection();
			dao = new BoardDao();
			writerCheck = writerCheck(userID, boardNo);

			if (writerCheck.equals("false")) {
				int boardcountIncrease = boardcountIncrease(boardNo);
			}

			boardDetailInfo = dao.boardDetailInfo(con, boardNo);
			logger.debug(boardDetailInfo + "<-boardDetailInfo");
		} catch (Exception e) {
			logger.error("boardDetailInfoerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}
		return boardDetailInfo;
	}

	// 글 작성
	public int boardWrite(String boardtitle, String boardContent, String userid) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		int boardWrite = 0;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardWrite = dao.boardWrite(con, boardtitle, boardContent, userid);
		} catch (Exception e) {
			logger.error("boardWriteerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}

		return boardWrite;
	}

	// 답글 작성
	public int boardReplyInsert(String boardtitle, String boardContent, String userid, BoardVo parentVo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		int boardReply = 0;
		int ReplyUpdate =0;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardReply = dao.boardReply(con, boardtitle, boardContent, userid, parentVo);
			ReplyUpdate = dao.ReplyUpdate(con, parentVo);
			logger.debug(boardtitle+"<-boardtitle");
			logger.debug(boardContent+"<-boardContent");
			logger.debug(userid+"<-userid");
			logger.debug(parentVo+"<-parentVo");
			logger.debug(boardReply+"<-boardReply");
		} catch (Exception e) {
			logger.error("boardWriteerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}

		return boardReply;
	}

	// 수정
	public int boardUpdate(String boardtitle, String boardContent, int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		int boardUpdate = 0;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardUpdate = dao.boardUpdate(con, boardtitle, boardContent, boardNo);
		} catch (Exception e) {
			logger.error("boardWriteerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}

		return boardUpdate;
	}

	// 삭제
	public int boardDelete(int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		int boardDelete = 0;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardDelete = dao.boardDelete(con, boardNo);
			logger.debug(boardDelete);

		} catch (Exception e) {
			logger.error("boardDetailInfoerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}
		return boardDelete;
	}

	// 게시물 작성자 확인
	public String writerCheck(String userID, int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		String writerCheck = "false";
		String getwriter = null;

		try {
			con = getConnection();
			dao = new BoardDao();
			getwriter = dao.writerCheck(con, boardNo);
			logger.debug(getwriter);

			if (getwriter.equals(userID)) {
				writerCheck = "true";
			}
		} catch (Exception e) {
			logger.error("writerCheckerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}
		return writerCheck;
	}

	// 조회수 증가
	private int boardcountIncrease(int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		int boardcountIncrease = 0;

		try {
			con = getConnection();
			dao = new BoardDao();
			boardcountIncrease = dao.boardcountIncrease(con, boardNo);
			logger.debug(boardcountIncrease);

		} catch (Exception e) {
			logger.error("boardDetailInfoerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}
		return boardcountIncrease;
	}

	// 댓글 리스트 가져오기
	public List<CommentVo> getuserCommentList(int boardNo) throws Exception {
		Connection con = null;
		BoardDao dao = null;
		List<CommentVo> getuserCommentList = null;

		try {
			con = getConnection();
			dao = new BoardDao();
			getuserCommentList = dao.getuserCommentList(con, boardNo);
		} catch (Exception e) {
			logger.error("getuserCommentListerror", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				throw (e);
			}
		}

		return getuserCommentList;
	}

	// 대댓글 처리
//		public List<CommentVo> setRecommentList(List<CommentVo> getuserCommentList) throws Exception {
//			Connection con = null;
//			BoardDao dao = null;
//			List<CommentVo> getuserCommentList2 = getuserCommentList;
	//
//			try {
//				con = getConnection();
//				dao = new BoardDao();
//				getuserCommentList = dao.getuserCommentList(con, boardNo);
//			} catch (Exception e) {
//				logger.error("getuserCommentListerror", e);
//				con.rollback();
//				throw (e);
//			} finally {
//				try {
//					con.close();
//				} catch (Exception e) {
//					throw (e);
//				}
//			}
	//
//			return getuserCommentList;
//		}

	// Connection
	private Connection getConnection() throws Exception {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("classFornameException", e);
			throw (e);
		}

		String url = "jdbc:postgresql://database-1.cluster-cismpcs6xtqh.ap-northeast-2.rds.amazonaws.com:5432/ojtdatabase";
		String id = "postgres";
		String pw = "icanmanojt!2";
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, id, pw);

		} catch (SQLException e) {
			// 예외처리일 경우에는 e.print가 아닌
			// throw를 해줘야함
			logger.error("getConnectionException", e);
			throw (e);
		}

		return con;
	}
}
