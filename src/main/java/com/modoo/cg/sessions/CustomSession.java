package com.modoo.cg.sessions;

import javax.servlet.http.HttpSession;

public class CustomSession{
		
	HttpSession session;
	

	
	public void setID(String Id) { // 아이디로 값을 받고 저장
		
		
		session.setAttribute("Sid", Id);
		
		System.out.println("세션 생성  = Sid :" + Id);
		
	}
	
	
	
	public String getId() {   // 아이디가 출력 됨
		
		String sID="";
		
		
		sID = (String) session.getAttribute("Sid");
		
	
		return sID;
		 
	}
	
	public boolean SessionCheck() { //세션이 존재 하는지 체크 여부
		
		String sID="";
		
		sID = (String) session.getAttribute(getId());
		
		System.out.println("세션 확인  = Sid :" + sID);
		
		 if(sID == null || sID.equals("") ) {
				
			 return false;
			 
		 }else {
			
			 return true;
			 
		 }
		
	}

	
	
	public boolean removeId(String sID) { //세션 삭제  키값으로 받음..
		
		System.out.println("세션 삭제  이름"+ sID);
		
		session.removeAttribute(sID);
		
		
		return true;
	}
	
	
	public String setSHA256() {
		
		
		return "";
	}
	
	
	//http://devsh.tistory.com/entry/%EC%84%B8%EC%85%98-%ED%83%80%EC%9E%84%EC%95%84%EC%9B%83-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
	public void getCSRF_token() {
		
		
		
	}
	
	
	public void setCSRF_token() {
		
		
		
	}
	
	
	public boolean TokenCheck() { //토큰이 있는지 없는지 체크
		
		
		
		return true;
	}
	
}
