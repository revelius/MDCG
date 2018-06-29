package com.modoo.cg.command;

import java.util.*;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;
import com.modoo.cg.dto.*;
import com.modoo.cg.paging.page;

public class MailListCom implements Command{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		Integer curPage1=(Integer)map.get("curPage");
		
		int curPage=curPage1.intValue();
		
		System.out.println("curPage : "+curPage);
		
		Dao dao=new Dao();
		
		ArrayList<MailDto> dto;
			
		int listlangth = dao.list().size();
			
		page p = new page(listlangth,curPage);
			
		dto = dao.mlistsize( p.getStartIndex(), p.getPageSize());
			
		Map<String,Object> ml = new HashMap<String,Object>();
			
		ml.put("list",dto);
		ml.put("listcnt", listlangth);
		ml.put("p",p);
		
		model.addAttribute("ml",ml);
	}

}