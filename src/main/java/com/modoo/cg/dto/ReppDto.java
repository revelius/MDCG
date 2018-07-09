package com.modoo.cg.dto;

public class ReppDto {
	
	
	private int num;
	private String bid; //아이디
	private int border_id; //게시글 번호
	private String content; //답글 글
	private int parent;//해당 글의 부모
	private int depth; // 댓글인지 대댓글인지
	private int orderid; //글의 순서..
	
	
	public ReppDto() {
		
	}
	
	
	
	public ReppDto(int num, String bid, int border_id, String content, int parent, int depth, int orderid) {
		
		this.num = num;
		this.bid = bid;
		this.border_id = border_id;
		this.content = content;
		this.parent = parent;
		this.depth = depth;
		this.orderid = orderid;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getBorder_id() {
		return border_id;
	}
	public void setBorder_id(int border_id) {
		this.border_id = border_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	
}
