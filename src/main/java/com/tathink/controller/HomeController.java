package com.tathink.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tathink.controller.common.TempleteSetter;
import com.tathink.database.model.Course;
import com.tathink.database.model.Event;
import com.tathink.database.model.Member;
import com.tathink.database.model.Notice;
import com.tathink.database.model.Qna;
import com.tathink.database.repository.CourseLevelRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.EventRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.NoticeRepositoryService;
import com.tathink.database.repository.QnaRepositoryService;

@Controller
public class HomeController {
	@Autowired
	TempleteSetter templeteSetter;
	@Autowired
	CourseLevelRepositoryService courseLevelRepositoryService;
	@Autowired
	EventRepositoryService eventRepositoryService;
	@Autowired
	CourseRepositoryService courseRepositoryService;
	@Autowired
	MemberRepositoryService memberRepositoryService;
	@Autowired
	QnaRepositoryService qnaRepositoryService;
	@Autowired
	NoticeRepositoryService noticeRepositoryService;
	
	
	@RequestMapping("/")
	public String home(Member member, Model model)
	{		
		System.out.println("home");
		
		List<Event> listEvent = eventRepositoryService.getAll();
		List<Course> listCourse = courseRepositoryService.getAllByBDelete(false, new Sort(Direction.DESC, "seq"));
		List<Member> listTeacher = memberRepositoryService.getAllByLevelBDelete(false, "LEVEL_TEACHER", new Sort(Direction.DESC, "regDate"));
		Page<Qna> pageQna = qnaRepositoryService.getAllPageable(10, 0, new Sort(Direction.DESC, "qRegDate"));
		Page<Notice> pageNotice = noticeRepositoryService.getAllPageable(10, 0, new Sort(Direction.DESC, "regDate"));
		
		templeteSetter.setHeader(member, model);
		
		model.addAttribute("currentDate", LocalDateTime.now());
		model.addAttribute("listNotice", pageNotice.getContent());
		model.addAttribute("listQna", pageQna.getContent());
		model.addAttribute("listTeacher", listTeacher);
		model.addAttribute("listCourse", listCourse);
		model.addAttribute("listEvent", listEvent);
		
		return "index";
	}
}
