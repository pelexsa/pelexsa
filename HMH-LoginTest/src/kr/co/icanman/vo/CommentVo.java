package kr.co.icanman.vo;

public class CommentVo {
	private int commentno;
	private int boardno;
	private String userid;
	private String username;
	private String boardcomment;
	private int groupid;
	private int parentcommentno;
	private int dept;
	private int orderby;

	public CommentVo() {
		super();
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBoardcomment() {
		return boardcomment;
	}

	public void setBoardcomment(String boardcomment) {
		this.boardcomment = boardcomment;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getParentcommentno() {
		return parentcommentno;
	}

	public void setParentcommentno(int parentcommentno) {
		this.parentcommentno = parentcommentno;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public int getOrderby() {
		return orderby;
	}

	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}

	@Override
	public String toString() {
		return "CommentVo [commentno=" + commentno + ", boardno=" + boardno + ", userid=" + userid + ", username="
				+ username + ", boardcomment=" + boardcomment + ", groupid=" + groupid + ", parentcommentno="
				+ parentcommentno + ", dept=" + dept + ", orderby=" + orderby + "]";
	}

}
