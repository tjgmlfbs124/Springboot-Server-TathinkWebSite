package com.tathink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tathink.controller.common.TempleteSetter;
import com.tathink.database.model.Member;
import com.tathink.database.repository.MemberRepositoryService;

@Controller
public class TatinkIntoController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@RequestMapping("/tathinkInfo/descript")
	public String descript(Member member, Model model)
	{	
		System.out.println("descript");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "descript");
		
		return "tathinkInfo/descript";
	}
	
	@RequestMapping("/tathinkInfo/intro")
	public String intro(Member member, Model model)
	{	
		System.out.println("intro");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "intro");
		
		return "tathinkInfo/intro";
	}
	
	@RequestMapping("/tathinkInfo/location")
	public String location(Member member, Model model)
	{	
		System.out.println("location");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "location");
		
		return "tathinkInfo/location";
	}
}
