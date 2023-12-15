package com.ji.springP001.Comment;

import java.util.Date;

public class Comment {

	private int c_no;
	private int c_b_no;
	private String c_owner;
	private String c_text;
	private Date c_when;

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int c_no, int c_b_no, String c_owner, String c_text, Date c_when) {
		super();
		this.c_no = c_no;
		this.c_b_no = c_b_no;
		this.c_owner = c_owner;
		this.c_text = c_text;
		this.c_when = c_when;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getC_b_no() {
		return c_b_no;
	}

	public void setC_b_no(int c_b_no) {
		this.c_b_no = c_b_no;
	}

	public String getC_owner() {
		return c_owner;
	}

	public void setC_owner(String c_owner) {
		this.c_owner = c_owner;
	}

	public String getC_text() {
		return c_text;
	}

	public void setC_text(String c_text) {
		this.c_text = c_text;
	}

	public Date getC_when() {
		return c_when;
	}

	public void setC_when(Date c_when) {
		this.c_when = c_when;
	}
	
	

}
