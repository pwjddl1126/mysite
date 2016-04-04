package com.estsoft.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private String reg_date;
	private long viewcount;
	private long group_no;
	private long order_no;
	private long depth;
	private long user_no;
	private String name;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getViewcount() {
		return viewcount;
	}
	public void setViewcount(long viewcount) {
		this.viewcount = viewcount;
	}
	public long getGroup_no() {
		return group_no;
	}
	public void setGroup_no(long group_no) {
		this.group_no = group_no;
	}
	public long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(long order_no) {
		this.order_no = order_no;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content="
				+ content + ", reg_date=" + reg_date + ", viewcount="
				+ viewcount + ", group_no=" + group_no + ", order_no="
				+ order_no + ", depth=" + depth + ", user_no=" + user_no
				+ ", name=" + name + "]";
	}
	

	
	
	
}
