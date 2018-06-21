package com.modoo.cg.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.modoo.cg.dao.Dao;
import com.modoo.cg.dto.Dto;

public class ListCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		
		Dao dao=new Dao();
		ArrayList<Dto> dtos=dao.list();
		
		model.addAttribute("list", dtos);
	}

}
