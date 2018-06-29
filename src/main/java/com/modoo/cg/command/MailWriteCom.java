package com.modoo.cg.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;

public class MailWriteCom implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		String post=request.getParameter("post");
		String send=request.getParameter("send");
		String contents=request.getParameter("contents");
		
		Dao dao=new Dao();
		dao.mailwrite(post, send, contents);
	}

}
