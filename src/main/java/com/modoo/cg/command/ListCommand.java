package com.modoo.cg.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;
import com.modoo.cg.dto.Dto;

public class ListCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		Integer curPage1=(Integer)map.get("curPage");
		String keyword1=(String)map.get("keyword");
		String searchOption1=(String)map.get("searchOption");
		
		int curPage=curPage1.intValue();
		String keyword=keyword1.toString();
		String searchOption=searchOption1.toString();
		
		System.out.println("curPage : "+curPage);
		System.out.println("keyword : "+keyword);
		System.out.println("searchOption : "+searchOption);
		
		Dao dao=new Dao();
		ArrayList<Dto> dtos;
		System.out.println(keyword.equals(""));
		if(keyword.equals("")||searchOption.equals("")) {
			
			dtos=dao.list();
			int listlangth= dao.list().size();
			
			System.out.println("listlangth : "+listlangth);
			
			
			
			/* 검색 구현중...
			 * Map<String,Object> viewmap = new HashMap<String,Object>();
			
			viewmap.put("list", list);
			viewmap.put("count", list);
			viewmap.put("searchOption", list);
			viewmap.put("keyword", list);
			viewmap.put("boardPage", list);
			
			*/
			
			
			model.addAttribute("list", dtos);
		}else {
			
			/*
			dtos=dao.list();
			int listlangth= dao.list().size();
			
			System.out.println("listlangth : "+listlangth);
			
			model.addAttribute("list", dtos);*/
		}
		
		
		
		
		
		
	}

}
