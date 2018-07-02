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
		//HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		Integer bID = (Integer)map.get("bID");  //게시글 번호
		Integer curPage = (Integer)map.get("curPage"); //페이지 번호
		String keyword1=(String)map.get("keyword");  //검색시 키워드
		String searchOption1=(String)map.get("searchOption");  //검색 기준 
		String limit1=(String)map.get("limit");   //게시글 제한
		String sessionNull=(String)map.get("sessionNull"); //게시글 제한 세션 확인
		
		String id = bID.toString();
		String keyword=keyword1.toString();
		String searchOption=searchOption1.toString();
		String limit = limit1.toString();
		String sessionNull1=sessionNull;
		
		
		
		/*contents view*/
		
		Dao dao=new Dao();
		Dto dto;
		dto=dao.constentView(id);
		
		/*contents view end*/
		
		
		ArrayList<Dto> dtoP;
		
		if(keyword.equals("")||searchOption.equals("")) {
		
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
			
		}else if( !keyword.equals("") && searchOption.equals("title") ) {  
			//제목 검색시..
			
			
			
			String getSearch="?searchOption="+searchOption+"&keyword="+keyword+"&sc=search";
			
			int listlangth = dao.listtitlesize(keyword).size(); 
			
			page  p = new page(listlangth,curPage);
			
			dtoP = dao.listtitleSearch( p.getStartIndex(), p.getPageSize(), keyword);
			
			Map<String,Object> vm = new HashMap<String,Object>();
			
			vm.put("curP",curPage);
			vm.put("list",dtoP);
			vm.put("listcnt", listlangth);
			vm.put("p",p);
			vm.put("content_view", dto);
			vm.put("keyword", keyword);
			vm.put("searchOption", searchOption);
			vm.put("getSearch", getSearch);
			if(sessionNull1 == null) {
						
				vm.put("sNull", 1); 
			}
					
					
			if(!limit.equals("")) {
						
				vm.put("limit", limit);
						
			}
			
			model.addAttribute("vm",vm);
			
		}
		
		
		
	}

}
