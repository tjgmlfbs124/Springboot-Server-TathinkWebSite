package com.tathink.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tathink.controller.common.TempleteSetter;
import com.tathink.controller.model.ViewOfflineStudyInfo;
import com.tathink.controller.model.ViewOnlineStudyInfo;
import com.tathink.database.model.Course;
import com.tathink.database.model.Kit;
import com.tathink.database.model.KitInfo;
import com.tathink.database.model.Member;
import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineClassMember;
import com.tathink.database.model.OfflineLesson;
import com.tathink.database.model.OfflineStudyInfo;
import com.tathink.database.model.OnlineLesson;
import com.tathink.database.model.OnlineStudyInfo;
import com.tathink.database.repository.CourseLevelRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.KitInfoRepositoryService;
import com.tathink.database.repository.KitRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.OfflineClassMemberRepositoryService;
import com.tathink.database.repository.OfflineClassRepositoryService;
import com.tathink.database.repository.OfflineLessonRepositoryService;
import com.tathink.database.repository.OfflineStudyInfoRepositoryService;
import com.tathink.database.repository.OnlineLessonRepositoryService;
import com.tathink.database.repository.OnlineStudyInfoRepositoryService;

@Controller
public class CourseController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	CourseRepositoryService courseRepositoryService;
	
	@Autowired
	CourseLevelRepositoryService courseLevelRepositoryService;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@Autowired
	OnlineStudyInfoRepositoryService onlineStudyInfoRepositoryService;
	
	@Autowired
	OnlineLessonRepositoryService onlineLessonRepositoryService;
	
	@Autowired
	OfflineStudyInfoRepositoryService offlineStudyInfoRepositoryService;
	
	@Autowired
	OfflineClassRepositoryService offlineClassRepositoryService;
	
	@Autowired
	OfflineClassMemberRepositoryService offlineClassMemberRepositoryService;
	
	@Autowired
	OfflineLessonRepositoryService offlineLessonRepositoryService;
	
	@Autowired
	KitInfoRepositoryService kitInfoRepositoryService;
	
	@Autowired
	KitRepositoryService kitRepositoryService;
	
	@RequestMapping("/course")
	public String course(Member member, String levelSeq, String courseSeq, Model model, RedirectAttributes redirectAttributes)
	{		
		System.out.println("course");
		
		try
		{
			Course selectedCourse = null;
			Optional<Member> teacher = null;		
			ViewOnlineStudyInfo onlineStudyInfoData = new ViewOnlineStudyInfo();
			ViewOfflineStudyInfo offlineStudyInfoData = new ViewOfflineStudyInfo();
			ArrayList<Kit> listKit = new ArrayList<Kit>();
			List<OnlineStudyInfo> tempListOnlineStudy;
			List<OfflineStudyInfo> tempListOfflineStudy;
			List<KitInfo> tempListKitInfo;
						
			selectedCourse = templeteSetter.setCourseLnb(levelSeq, courseSeq, model);
			templeteSetter.setHeader(member, model);

			if(selectedCourse==null){
				redirectAttributes.addAttribute("error","해당과정에 과목정보가 없습니다.");
				return "redirect:/course/errorPage";
			}			
			
			String levelName = courseLevelRepositoryService.findLevelName(selectedCourse.getLevelSeq());
			teacher = memberRepositoryService.findById(selectedCourse.getTeacherUsername());
			tempListOnlineStudy = onlineStudyInfoRepositoryService.getAllByCourseSeqBDelete(selectedCourse.getSeq(), false, new Sort(Direction.ASC, "no"));
			tempListOfflineStudy = offlineStudyInfoRepositoryService.getAllByCourseSeqBDelete(selectedCourse.getSeq(), false, new Sort(Direction.ASC, "no"));
			tempListKitInfo = kitInfoRepositoryService.getAllByCourseSeq(selectedCourse.getSeq());
			
			for(OnlineStudyInfo info : tempListOnlineStudy)
			{
				Optional<Member> studyTeacher = memberRepositoryService.findById(info.getTeacherUsername());
				
				if(studyTeacher.isPresent() == false)
				{
					onlineStudyInfoData.AddOnlineStudyInfoData(info, null );
				}
				else
				{
					onlineStudyInfoData.AddOnlineStudyInfoData(info, studyTeacher.get());
				}
			}
			
			for(OfflineStudyInfo info : tempListOfflineStudy)
			{
				OfflineClass offlineClass = offlineClassRepositoryService.findActiveClassByOfflineStudySeq(info.getSeq());
				
				if(offlineClass == null)
				{
					offlineStudyInfoData.AddOfflineStudyInfoData(info, null, null);
				}
				else
				{
					Optional<Member> studyTeacher = memberRepositoryService.findById(offlineClass.getTeacherUsername());
					
					if(studyTeacher.isPresent() == false)
					{
						offlineStudyInfoData.AddOfflineStudyInfoData(info, offlineClass, null);
					}
					else
					{
						offlineStudyInfoData.AddOfflineStudyInfoData(info, offlineClass, studyTeacher.get());
					}
				}
			}
			
			for(KitInfo info : tempListKitInfo)
			{
				Optional<Kit> kit = kitRepositoryService.findBySeq(info.getKitSeq());
				
				if(kit.isPresent() == true)
					listKit.add(kit.get());
			}
			
			model.addAttribute("levelName", levelName);
			model.addAttribute("course", selectedCourse);
			model.addAttribute("teacher", teacher.get());
			model.addAttribute("onlineStudyInfoData", onlineStudyInfoData);
			model.addAttribute("offlineStudyInfoData", offlineStudyInfoData);
			model.addAttribute("listKit", listKit);
			
			return "/course/course";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","과목정보를 불러오는 중 오류가 발생하였습니다.");
			return "redirect:/course/errorPage";
		}
	}
	
	@RequestMapping("/course/offlineclass")
	public String offlineclass(Member member, String offlineStudyInfoSeq, Model model , RedirectAttributes redirectAttributes)
	{		
		System.out.println("offlineclass");
		
		try
		{
			Course course = null;
			boolean inClass = false;
			if(member==null)
				member = new Member();
			List<OfflineClassMember> tempClassMember = offlineClassMemberRepositoryService.getClassSeqByUserName(member.getUsername());
					
			Optional<OfflineStudyInfo> offlineStudyInfo = offlineStudyInfoRepositoryService.findById(Integer.parseInt(offlineStudyInfoSeq));
			OfflineClass offlineClass = offlineClassRepositoryService.findActiveClassByOfflineStudySeq(offlineStudyInfo.get().getSeq());
			Optional<Member> teacher = null;		
			List<OfflineLesson> listOfflineLesson = null;
			
			if(tempClassMember!=null && tempClassMember.size()>0){
				for(OfflineClassMember classMember : tempClassMember){
					if(offlineClass.getSeq() ==classMember.getClassSeq()){
						inClass=true;
					}
				}
			}
			
			course = templeteSetter.setCourseLnb(null, ""+offlineStudyInfo.get().getCourseSeq(), model);
			templeteSetter.setHeader(member, model);
	
			teacher = memberRepositoryService.findById(offlineClass.getTeacherUsername());
			listOfflineLesson = offlineLessonRepositoryService.getAllByOfflineClassSeq(offlineClass.getSeq(), new Sort(Direction.ASC, "lessonOrder"));
			
			model.addAttribute("inClass", inClass);
			model.addAttribute("course", course);
			model.addAttribute("studyInfo", offlineStudyInfo.get());
			model.addAttribute("class", offlineClass);
			model.addAttribute("teacher", teacher.get());
			model.addAttribute("listLesson", listOfflineLesson);
			
			return "/course/offlineClass";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","오프라인 수업 정보 읽어오는 중 오류가 발생하였습니다.");
			return "redirect:/course/errorPage";
		}
	}
	                         
	@RequestMapping("/course/onlineClass")
	public String onlineclass(Member member, String onlineStudyInfoSeq, Model model, RedirectAttributes redirectAttributes)
	{		
		System.out.println("onlineclass");
		
		try
		{
			Course course = null;
			Optional<OnlineStudyInfo> onlineStudyInfo = onlineStudyInfoRepositoryService.findById(Integer.parseInt(onlineStudyInfoSeq));
			Optional<Member> teacher = null;		
			List<OnlineLesson> listOnlineLesson = null;
			
			course = templeteSetter.setCourseLnb(null, ""+onlineStudyInfo.get().getCourseSeq(), model);
			templeteSetter.setHeader(member, model);
	
			teacher = memberRepositoryService.findById(onlineStudyInfo.get().getTeacherUsername());
			listOnlineLesson = onlineLessonRepositoryService.getAllByOnlineStudyInfoSeq(onlineStudyInfo.get().getSeq(), new Sort(Direction.ASC, "lessonOrder"));
			
			model.addAttribute("course", course);
			model.addAttribute("studyInfo", onlineStudyInfo.get());
			model.addAttribute("teacher", teacher.get());
			model.addAttribute("listLesson", listOnlineLesson);
			
			return "/course/onlineClass";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","온라인 수업 정보 읽어오는 중 오류가 발생하였습니다.");
			return "redirect:/course/errorPage";
		}
	}	
	
}
