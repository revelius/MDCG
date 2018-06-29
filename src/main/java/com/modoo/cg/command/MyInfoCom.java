package com.modoo.cg.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

import com.modoo.cg.dao.*;
import com.modoo.cg.dto.*;
import com.modoo.cg.paging.page;

public class MyInfoCom implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map=model.asMap();

		Dao dao=new Dao();
		
		ArrayList<RegisterDto> dto;
		
		String id="곰";
		dto = dao.myinfo(id);
			
		Map<String,Object> info= new HashMap<String,Object>();
			
		info.put("list", dto);
			
		model.addAttribute("info", info);
	}

}
