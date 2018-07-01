package com.modoo.cg.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;
import com.modoo.cg.dto.Dto;
import com.modoo.cg.paging.page;

public class ContentCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		Integer bID = (Integer)map.get("bID");
		Integer curPage = (Integer)map.get("curPage");
		String id = bID.toString();
		
		String limit1=(String)map.get("limit");
		String sessionNull=(String)map.get("sessionNull");
		
		String limit = limit1.toString();
		String sessionNull1=sessionNull;
		
		
		Dao dao=new Dao();
		Dto dto;
		dto=dao.constentView(id);
		
		
		
		ArrayList<Dto> dtoP;
	
		int listlangth = dao.list().size();
		
		System.out.println("listlangth : "+listlangth);
		
		page p = new page(listlangth,curPage);
		
		System.out.println("startindex :"+ p.getStartIndex());
		System.out.println("pagesize :"+p.getPageSize());
		
		dtoP = dao.listsize( p.getStartIndex(), p.getPageSize());
		
		Map<String,Object> vm = new HashMap<String,Object>();
		
		
		vm.put("curP",curPage);
		vm.put("list",dtoP);
		vm.put("listcnt", listlangth);
		vm.put("p",p);
		vm.put("content_view", dto);
		
		if(sessionNull1 == null) {
			
			vm.put("sNull", 1); 
		}
		
		
		if(!limit.equals("")) {
			
			vm.put("limit", limit);
			
		}
		
		
		model.addAttribute("vm",vm);
		
		
		
		
	}

}
