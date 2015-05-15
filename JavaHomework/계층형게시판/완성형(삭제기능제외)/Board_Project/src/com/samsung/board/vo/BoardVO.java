package com.samsung.board.vo;

import java.sql.Date;

public class BoardVO{
	private int seq;
	private int family;
	private int group_seq;
	private int depth;
	private String title;
	private String nickname;
	private String content;
	private Date regdate;
	private int cnt;
	private String userid;
	private String parent;

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getFamily() {
		return family;
	}
	public void setFamily(int family) {
		this.family = family;
	}
	public int getGroup_seq() {
		return group_seq;
	}
	public void setGroup_seq(int group_seq) {
		this.group_seq = group_seq;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", family=" + family + ", group_seq="
				+ group_seq + ", depth=" + depth + ", title=" + title
				+ ", nickname=" + nickname + ", content=" + content
				+ ", regdate=" + regdate + ", cnt=" + cnt + ", userid="
				+ userid + "]";
	}
	
}