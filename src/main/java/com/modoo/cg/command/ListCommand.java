package com.modoo.cg.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;
import com.modoo.cg.dto.Dto;
import com.modoo.cg.paging.page;

public class ListCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		Integer curPage1=(Integer)map.get("curPage");
		String keyword1=(String)map.get("keyword");
		String searchOption1=(String)map.get("searchOption");
		String limit1=(String)map.get("limit");
		String sessionNull=(String)map.get("sessionNull");
		
		int curPage=curPage1.intValue();
		String keyword=keyword1.toString();
		String searchOption=searchOption1.toString();
		String limit = limit1.toString();
		String sessionNull1=sessionNull;
		
		System.out.println("curPage : "+curPage);
		System.out.println("keyword : "+keyword);
		System.out.println("searchOption : "+searchOption);
		
		Dao dao=new Dao();
		
		ArrayList<Dto> dto;
		System.out.println(keyword.equals(""));
		
		
		if(keyword.equals("")||searchOption.equals("")) {
			//그냥 나올때..
			
			int listlangth = dao.list().size();
			
			System.out.println("listlangth : "+listlangth);
			
			page p = new page(listlangth,curPage);
			
			System.out.println("startindex :"+ p.getStartIndex());
			System.out.println("pagesize :"+p.getPageSize());
			
			dto = dao.listsize( p.getStartIndex(), p.getPageSize());
			
			Map<String,Object> vm = new HashMap<String,Object>();
			
			vm.put("list",dto);
			vm.put("listcnt", listlangth);
			vm.put("p",p);
			if(sessionNull1 == null) {
				
				vm.put("sNull", 1); 
			}
			
			
			if(!limit.equals("")) {
				
				vm.put("limit", limit);
				
			}
			
			
			model.addAttribute("vm",vm);
			
		}else if( !keyword.equals("") && searchOption.equals("title") ) {  
			//제목 검색시..
			
			
			int listlangth = dao.listtitlesize(keyword).size(); 
			
			page  p = new page(listlangth,curPage);
			
			dto = dao.listtitleSearch( p.getStartIndex(), p.getPageSize(), keyword);
			
			Map<String,Object> vm = new HashMap<String,Object>();
			
			vm.put("list",dto);
			vm.put("listcnt", listlangth);
			vm.put("p",p);
			vm.put("keyword", keyword);
			vm.put("searchOption", searchOption);
			model.addAttribute("vm",vm);
			
		}else if( !keyword.equals("") && searchOption.equals("content")  ) {
				
		}
	}

}
