package com.tathink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tathink.controller.common.TempleteSetter;
import com.tathink.database.model.Course;
import com.tathink.database.model.Member;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.NoticeRepositoryService;
import com.tathink.database.repository.QnaRepositoryService;
import com.tathink.database.repository.QuestionGroupEduRepositoryService;

@Controller
public class ErrorPageController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@Autowired
	NoticeRepositoryService noticeRepositoryService;
	
	@Autowired
	QnaRepositoryService qnaRepositoryService;
	
	@Autowired
	QuestionGroupEduRepositoryService questionGroupEduRepositoryService;
	
	@RequestMapping("/course/errorPage")
	public String courseErrorPage(Member member, String error, Model model)
	{	
		Course dummy = new Course();
		System.out.println("courseErrorPage");
		templeteSetter.setHeader(member, model);
		templeteSetter.setCourseLnb(null, null, model);
		
		dummy.setSeq(-1);
		model.addAttribute("course", dummy);
		model.addAttribute("error", error);
		return "course/errorPage";
	}
	
	@RequestMapping("/board/errorPage")
	public String boardErrorPage(Member member, String error, Model model)
	{	
		System.out.println("boardErrorPage");
		templeteSetter.setHeader(member, model);
		model.addAttribute("error", error);
		return "board/errorPage";
	}
	
	@RequestMapping("/join/errorPage")
	public String joinErrorPage(Member member, String error, Model model)
	{	
		System.out.println("joinErrorPage");
		templeteSetter.setHeader(member, model);
		model.addAttribute("error", error);
		return "join/errorPage";
	}
	
	@RequestMapping("/teacher/errorPage")
	public String teacherErrorPage(Member member, String error, Model model)
	{	
		System.out.println("teacherErrorPage");
		templeteSetter.setHeader(member, model);
		model.addAttribute("error", error);
		return "teacher/errorPage";
	}
	
	@RequestMapping("/myroom/errorPage")
	public String myroomErrorPage(Member member, String error, Model model)
	{	
		System.out.println("/myroom/errorPage");
		templeteSetter.setHeader(member, model);
		model.addAttribute("error", error);
		return "/myroom/errorPage";
	}
}
