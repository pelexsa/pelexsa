package kr.co.icanman.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import kr.co.icanman.dao.LoginDao;
import kr.co.icanman.vo.LoginVo;

public class LoginService {

	protected Logger logger = Logger.getRootLogger();
	
	// 화면 전환 메소드 실행
	public String viewChange(String userID, String userPassword) throws Exception {
		String viewChange = viewChangeLogic(userID,userPassword);
		return viewChange;
	}

	private String viewChangeLogic(String userID, String userPassword) throws Exception {
		String viewChange = "error";
		Connection con= null;
		
		try {
			con = getConnection();
			LoginDao dao = new LoginDao();

			// 로그인 체크 여부
			int checkLogin = dao.checkLogin(con, userID, userPassword);
			int loginFalsecount = dao.loginFalsecount(con, userID);

			// 5번이상 로그인 제한
			if (loginFalsecount >= 5) {
				viewChange = "ban";
			}

			// 5번 미만일 경우에는 아이디,비밀번호등이 맞는지 체크
			// 로그인 성공시 시간 초기화
			else if (checkLogin == 1) {
				int result = dao.updateSuccesstime(con, userID);
				viewChange =  "success";

			}
			
			// 로그인 실패
			// 로그인 실패시 실패 회수 증가
			else if (checkLogin == 0) {
				int result = dao.falseCountincrease(con, userID);
				viewChange = "false";
			}
			
			// 아이디 없음, 데이터베이스 오류
			else {
				viewChange = "error";
			}
		} catch (Exception e) {
			logger.error("viewChangeException", e);
			con.rollback();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				logger.error("serVicefinalException", e);
				throw (e);
			}
		}

		return viewChange;
	}
	
	// 유저 정보 메소드 실행
	public LoginVo findUserinfo(String userID, String userPassword) throws Exception {
		LoginVo findUserinfo = findUserinfoLogic(userID,userPassword);
		return findUserinfo;
	}

	// 유저 정보 가져오기
	private LoginVo findUserinfoLogic(String userID, String userPassword) throws Exception {
		LoginDao dao = null;
		LoginVo findUserinfoLogic = null;
		Connection con= null;
		
		try {
			dao = new LoginDao();
			con = getConnection();

			findUserinfoLogic = dao.getUserinfo(con, userID);
			
		} catch (Exception e) {
			logger.error("findUserinfoException", e);
			con.rollback();
			throw (e);
		} finally {
			con.close();
		}
		return findUserinfoLogic;
	}

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
			logger.error("getConnectionException", e);
			throw (e);
		}

		return con;
	}
}
