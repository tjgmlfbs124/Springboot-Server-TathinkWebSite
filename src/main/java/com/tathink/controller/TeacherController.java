package com.tathink.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tathink.controller.common.TempleteSetter;
import com.tathink.database.model.Member;
import com.tathink.database.repository.MemberRepositoryService;

@Controller
public class TeacherController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@RequestMapping("/teacher/listTeacher")
	public String listTeacher(Member member, String selectId, Model model)
	{	
		System.out.println("listTeacher");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listTeacher");
		
		if(selectId == null)
			selectId = "";
		
		model.addAttribute("selectId", selectId);
		
		return "teacher/listTeacher";
	}
	
	@RequestMapping("/teacher/recruitTeacher")
	public String recruitTeacher(Member member, Model model)
	{	
		System.out.println("listTeacher");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "recruitTeacher");
		
		return "teacher/recruitTeacher";
	}
}
