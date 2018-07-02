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
	
}
