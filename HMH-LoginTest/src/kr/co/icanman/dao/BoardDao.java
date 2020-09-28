package kr.co.icanman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kr.co.icanman.vo.BoardVo;
import kr.co.icanman.vo.CommentVo;

public class BoardDao {

	protected Logger logger = Logger.getRootLogger();

	// 게시물 전체 출력
	public List<BoardVo> boardListAll(Connection con) throws SQLException {
		String SQL = "select (row_number() over(order by boarddate desc)) as rownum,userID,boardNo,boardTitle,boardContent\r\n" + 
				",boardDate,boardHit,boardGroup,boardSequence,boardLevel \r\n" + 
				"from hmh.userboardtest\r\n" + 
				"order by boardGroup desc, boardsequence asc;";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BoardVo> list = null;

		try {
			pstm = con.prepareStatement(SQL);
			rs = pstm.executeQuery();
			list = new ArrayList<BoardVo>();

			while (rs.next()) {
				BoardVo boardVo = new BoardVo();
				boardVo.setRownum(rs.getInt("rownum"));
				boardVo.setUserID(rs.getString("userID"));
				boardVo.setBoardNo(rs.getInt("boardNo"));
				boardVo.setBoardTitle(rs.getString("boardTitle"));
				boardVo.setBoardContent(rs.getString("boardContent"));
				boardVo.setBoardDate(rs.getTimestamp("boardDate"));
				boardVo.setBoardHit(rs.getInt("boardHit"));
				boardVo.setBoardGroup(rs.getInt("boardGroup"));
				boardVo.setBoardSequence(rs.getInt("boardSequence"));
				boardVo.setBoardLevel(rs.getInt("boardLevel"));
				list.add(boardVo);
			}
		} catch (SQLException e) {
			logger.error("checkLoginException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}
		return list;
	}

	// 게시물 하나 출력
	public BoardVo boardDetailInfo(Connection con, int boardNo) throws SQLException {
		String SQL = "select userID,boardNo,boardTitle,boardContent,boardDate,boardHit,boardGroup,boardSequence,boardLevel \r\n" + 
				"from hmh.userboardtest\r\n" + 
				"where boardno=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BoardVo boardVo = null;
		
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, boardNo);
			rs = pstm.executeQuery();

			while (rs.next()) {
				boardVo = new BoardVo();
				
				boardVo.setUserID(rs.getString("userID"));
				boardVo.setBoardNo(rs.getInt("boardNo"));
				boardVo.setBoardTitle(rs.getString("boardTitle"));
				boardVo.setBoardContent(rs.getString("boardContent"));
				boardVo.setBoardDate(rs.getTimestamp("boardDate"));
				boardVo.setBoardHit(rs.getInt("boardHit"));
				boardVo.setBoardGroup(rs.getInt("boardGroup"));
				boardVo.setBoardSequence(rs.getInt("boardSequence"));
				boardVo.setBoardLevel(rs.getInt("boardLevel"));
			}
		} catch (SQLException e) {
			logger.error("boardDetailInfoException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}
		return boardVo;
	}

	// 게시물 작성
	public int boardWrite(Connection con, String boardtitle, String boardContent, String userid) throws SQLException {
		String SQL = "insert into hmh.userboardtest \r\n" + 
				"values (?,nextval('testboardno'),?,?,now(),0,(select coalesce(max(boardgroup)+1 ,0)from hmh.userboardtest),0,0)";
		PreparedStatement pstm = null;
		int boardWrite = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userid);
			pstm.setString(2, boardtitle);
			pstm.setString(3, boardContent);
			boardWrite = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("boardWriteException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return boardWrite;
	}
	
	// 답글 작성
	public int boardReply(Connection con, String boardtitle,String boardContent,String userid, BoardVo parentVo) throws SQLException {
		String SQL = "insert into hmh.userboardtest \r\n" + 
				"values (?,nextval('testboardno'),?,?,now(),0,?,?,?)";
		PreparedStatement pstm = null;
		int boardWrite = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userid);
			pstm.setString(2, boardtitle);
			pstm.setString(3, boardContent);
			pstm.setInt(4, parentVo.getBoardGroup());
			pstm.setInt(5, parentVo.getBoardSequence()+1);
			pstm.setInt(6, parentVo.getBoardLevel()+1);
			boardWrite = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("boardReplyException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return boardWrite;
	}

	// 게시물 수정
	public int boardUpdate(Connection con, String boardtitle, String boardContent, int boardNo) throws SQLException {
		String SQL = "update hmh.userboardtest set boardtitle=?,boardcontent=? where boardno=?";
		PreparedStatement pstm = null;
		int result = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, boardtitle);
			pstm.setString(2, boardContent);
			pstm.setInt(3, boardNo);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("boardUpdateException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return result;
	}

	// 게시물 삭제
	public int boardDelete(Connection con, int boardNo) throws SQLException {
		String SQL = "delete from hmh.userboardtest\r\n" + "where boardno = ?";
		PreparedStatement pstm = null;
		int result = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, boardNo);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("boardDeleteException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return result;
	}

	// 게시물 작성자 확인
	public String writerCheck(Connection con, int boardNo) throws SQLException {
		String SQL = "select userid from hmh.userboardtest where boardno=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String getBoarduserid = null;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, boardNo);
			rs = pstm.executeQuery();

			while (rs.next()) {
				getBoarduserid = rs.getString("userID");
			}

		} catch (SQLException e) {
			logger.error("writerCheckException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return getBoarduserid;
	}

	// 조회수 증가
	public int boardcountIncrease(Connection con, int boardNo) throws SQLException {
		String SQL = "update hmh.userboardtest set boardhit = boardhit+1 where boardno=?";
		PreparedStatement pstm = null;
		int boardcountIncrease = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, boardNo);
			boardcountIncrease = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("boardcountIncreaseException", e);
			throw (e);
		} finally {
			pstm.close();
		}
		return boardcountIncrease;
	}

	// 게시물 댓글 출력
	public List<CommentVo> getuserCommentList(Connection con, int boardNo) throws SQLException {
		String SQL = "select l.username,commentno,boardcomment,dept \r\n" + "from hmh.usercomment c \r\n"
				+ "join hmh.USERLOGIN l using (userid)\r\n" + "where boardno = ?\r\n" + "order by groupid asc\r\n"
				+ "		,orderby asc;";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CommentVo> list = null;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setInt(1, boardNo);
			rs = pstm.executeQuery();
			list = new ArrayList<CommentVo>();

			while (rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setUsername(rs.getString("username"));
				vo.setCommentno(rs.getInt("commentno"));
				vo.setBoardcomment(rs.getString("boardcomment"));
				vo.setDept(rs.getInt("dept"));

				list.add(vo);
			}
		} catch (SQLException e) {
			logger.error("getuserCommentListException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}
		return list;
	}
}
