package com.modoo.cg.dto;

import java.sql.Timestamp;

public class MailDto {
	int num;
	String post;
	String send;
	String contents;
	String post_get;
	String send_get;
	String post_date;
	String send_date;
	String post_del;
	String send_del;

	public MailDto() {
		
	}
	
	public MailDto(int num, String post, String send, String contents, String post_get, String send_get, String post_date, String send_date, String post_del, String send_del) {
		this.num=num;
		this.post=post;
		this.send=send;
		this.contents=contents;
		this.post_get=post_get;
		this.send_get=send_get;
		this.post_date=post_date;
		this.send_date=send_date;
		this.post_del=post_del;
		this.send_del=send_del;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPost_get() {
		return post_get;
	}

	public void setPost_get(String post_get) {
		this.post_get = post_get;
	}

	public String getSend_get() {
		return send_get;
	}

	public void setSend_get(String send_get) {
		this.send_get = send_get;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getPost_del() {
		return post_del;
	}

	public void setPost_del(String post_del) {
		this.post_del = post_del;
	}

	public String getSend_del() {
		return send_del;
	}

	public void setSend_del(String send_del) {
		this.send_del = send_del;
	}
	
}
	
