package com.modoo.cg.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;

public class RegisterCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Dao dao=new Dao();
		dao.write(name, title, content);
	}

}

