package kr.co.icanman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import kr.co.icanman.vo.LoginVo;

public class LoginDao {

	private PreparedStatement pstm;
	private ResultSet rs;
	private String SQL;
	protected Logger logger = Logger.getRootLogger();

	// 유저가 입력한 아이디, 비밀번호가 맞는지 확인
	public int checkLogin(Connection con, String userID, String userPassword) throws Exception {
		SQL = "select userpassword from hmh.userlogintest where userid = ? ;";
		int checkLogin = -1;// 아이디 없음 디폴트 값
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			logger.debug(userID);
			logger.debug(userPassword);
			logger.debug(pstm.toString());
			if (rs.next()) {
				if (rs.getString("userpassword").equals(userPassword)) {
					checkLogin = 1; // 로그인 성공
				} else {
					checkLogin = 0; // 로그인 실패
				}
			}
		} catch (SQLException e) {
			logger.error("checkLoginException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}
		return checkLogin;
	}

	// 유저정보 받아오기
	public LoginVo getUserinfo(Connection con, String userID) throws Exception {
		SQL = "select userid,username,userpassword,loginfalsecount,loginsuccessdate from hmh.userlogintest where userid = ?;";
		LoginVo getUserinfo = null;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if (rs.next()) {
				getUserinfo = new LoginVo();
				getUserinfo.setUserID(rs.getString("userid"));
				getUserinfo.setUserName(rs.getString("username"));
				getUserinfo.setUserPassword(rs.getString("userpassword"));
				getUserinfo.setLoginFalsecount(rs.getInt("loginfalsecount"));
				getUserinfo.setLoginSuccessdate(rs.getTimestamp("loginsuccessdate"));
			}
		} catch (SQLException e) {
			logger.error("getUserinfoException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}
		return getUserinfo;
	}

	// 로그인 성공시 시간 최신화
	public int updateSuccesstime(Connection con, String userID) throws Exception {
		SQL = "update hmh.userlogintest set loginsuccessdate=now() where userid=?;";
		int updateSuccesstime = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
			updateSuccesstime = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("updateSuccesstimeException", e);
			throw (e);
		} finally {
			pstm.close();
		}

		return updateSuccesstime;
	}

	// 로그인 실패횟수 가져오기
	public int loginFalsecount(Connection con, String userID) throws Exception {
		SQL = "select loginfalsecount from hmh.userlogintest where userid=?;";
		int loginFalsecount = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();

			if (rs.next()) {
				loginFalsecount = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("loginFalsecountException", e);
			throw (e);
		} finally {
			pstm.close();
		}

		return loginFalsecount;
	}

	// 로그인 실패횟수 증가
	public int falseCountincrease(Connection con, String userID) throws Exception {
		SQL = "update hmh.userlogintest set loginfalsecount=loginfalsecount+1 where userid= ? ;";
		int falseCountincrease = 0;

		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);
			falseCountincrease = pstm.executeUpdate();

		} catch (SQLException e) {
			logger.error("falseCountincreaseException", e);
			throw (e);
		} finally {
			pstm.close();
			rs.close();
		}

		return falseCountincrease;
	}

}
