package kr.co.icanman.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardVo {
	private int rownum;
	private String userID;
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardHit;
	private int boardGroup;
	private int boardSequence;
	private int boardLevel;

	public BoardVo() {
		super();
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Timestamp boardDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formatDay = simpleDateFormat.format(boardDate);

		this.boardDate = formatDay;
	}

	public int getBoardHit() {
		return boardHit;
	}

	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}

	public int getBoardGroup() {
		return boardGroup;
	}

	public void setBoardGroup(int boardGroup) {
		this.boardGroup = boardGroup;
	}

	public int getBoardSequence() {
		return boardSequence;
	}

	public void setBoardSequence(int boardSequence) {
		this.boardSequence = boardSequence;
	}

	public int getBoardLevel() {
		return boardLevel;
	}

	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}

	@Override
	public String toString() {
		return "BoardVo [rownum=" + rownum + ", userID=" + userID + ", boardNo=" + boardNo + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardDate=" + boardDate + ", boardHit=" + boardHit
				+ ", boardGroup=" + boardGroup + ", boardSequence=" + boardSequence + ", boardLevel=" + boardLevel
				+ "]";
	}

}