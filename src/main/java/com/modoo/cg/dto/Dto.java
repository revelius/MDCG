package com.modoo.cg.dto;

import java.sql.Timestamp;

public class Dto {
	
	int id; // primary key auto_increment 게시판 글번호임
	String name; // 작성자 이름
	String title; // 제목
	String content; // 내용
	Timestamp date; // 작성일
	int hit; // 조회수
	int group; // 첫번째 댓글의 번호
	int step; // 댓글의 순서
	int indent; // 댓글의 깊이
	
	String tes="test"; //깃허브 연동용
	
	public Dto() {
		
	}
	
	public Dto(int id, String name,	String title, String content, Timestamp date, int hit, int group, int step,	int indent) {
		this.id=id;
		this.name=name;
		this.title=title;
		this.content=content;
		this.date=date;
		this.hit=hit;
		this.group=group;
		this.step=step;
		this.indent=indent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}
	
	
}
