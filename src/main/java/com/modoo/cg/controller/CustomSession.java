package com.modoo.cg.controller;

import javax.servlet.http.HttpSession;

public class CustomSession {
	HttpSession session;
	
	public CustomSession() {
		System.out.println("==================================================");
		System.out.println("봐라 세션이 생성되었느니라");
		System.out.println("==================================================");
	}
	
	public HttpSession addSession() {
		return this.session;
	}
	
	public HttpSession setId(HttpSession session, String id) {
		session.setAttribute("Sid", id);
		
		return session;
	}
	
	public String getId(HttpSession session) {
		String sId=(String)session.getAttribute("Sid");
		
		return sId;
	}
}
