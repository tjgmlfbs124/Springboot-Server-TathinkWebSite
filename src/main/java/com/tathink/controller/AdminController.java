package com.tathink.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tathink.TathinkApplication;
import com.tathink.controller.common.TempleteSetter;
import com.tathink.controller.model.ReqCourse;
import com.tathink.controller.model.ReqKit;
import com.tathink.controller.model.ReqOfflineClass;
import com.tathink.controller.model.ViewOfflineStudyInfo;
import com.tathink.database.model.Course;
import com.tathink.database.model.CourseLevel;
import com.tathink.database.model.Kit;
import com.tathink.database.model.KitInfo;
import com.tathink.database.model.Member;
import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineLessonList;
import com.tathink.database.model.OfflineStudyInfo;
import com.tathink.database.repository.CourseLevelRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.KitInfoRepositoryService;
import com.tathink.database.repository.KitRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.NoticeRepositoryService;
import com.tathink.database.repository.OfflineClassRepositoryService;
import com.tathink.database.repository.OfflineLessonRepositoryService;
import com.tathink.database.repository.OfflineStudyInfoRepositoryService;
import com.tathink.database.repository.QnaRepositoryService;
import com.tathink.database.repository.QuestionGroupEduRepositoryService;
import com.tathink.util.MyFileUtil;

@Controller
public class AdminController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@Autowired
	CourseLevelRepositoryService courseLevelRepository;
	
	@Autowired
	CourseRepositoryService courseRepositoryService;
	
	@Autowired
	OfflineStudyInfoRepositoryService offlineStudyInfoRepositoryService;
	
	@Autowired
	OfflineClassRepositoryService offlineClassRepositoryService;	
	
	@Autowired
	OfflineLessonRepositoryService offlineLessonRepositoryService;

	@Autowired
	KitInfoRepositoryService kitInfoRepositoryService;
	
	@Autowired
	KitRepositoryService kitRepositoryService;
	
	@Autowired
	NoticeRepositoryService noticeRepositoryService;
	
	@Autowired
	QnaRepositoryService qnaRepositoryService;
	
	@Autowired
	QuestionGroupEduRepositoryService questionGroupEduRepositoryService;
	
	@RequestMapping("/admin/listMemberInfo")
	public String memberInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("listMemberInfo");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listMemberInfo");
		
		return "admin/listMemberInfo";
	}
	
	@RequestMapping("/admin/listCourseInfo")
	public String courseInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("listCourseInfo");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listCourseInfo");
		
		return "admin/listCourseInfo";
	}
	
	@RequestMapping("/admin/listClassInfo")
	public String classInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("listClassInfo");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listClassInfo");
		
		return "admin/listClassInfo";
	}
	
	@RequestMapping("/admin/listKitInfo")
	public String kitInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("listKitInfo");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listKitInfo");
		
		return "admin/listKitInfo";
	}
	
	@RequestMapping("/admin/courseManage")
	public String courseManage(Member member, String courseSeq, Model model, RedirectAttributes redirectAttributes)
	{	
		ArrayList<Kit> listKit = new ArrayList<Kit>();
		List<KitInfo> tempListKitInfo;
		
		int seq = -1;
		
		if(courseSeq==null || courseSeq==""){
			System.out.println("courseManage");	
		}
		else{
			System.out.println("courseManage seq=" + courseSeq);
			try{
				seq = Integer.parseInt(courseSeq);
			}
			catch(Exception e){
				e.printStackTrace();
				redirectAttributes.addAttribute("error","과목정보를 불러오는중 오류가 발생했습니다.");
				return "redirect:admin/errorPage";
			}
			
		}

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		
		try{
			List<CourseLevel> courseLevelList = courseLevelRepository.getAll();
			
			List<OfflineStudyInfo> tempListOfflineStudy;
			ViewOfflineStudyInfo offlineStudyInfoData = new ViewOfflineStudyInfo();
			tempListOfflineStudy = offlineStudyInfoRepositoryService.getAllByCourseSeqBDelete(seq, false, new Sort(Direction.ASC, "no"));
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
			
			tempListKitInfo = kitInfoRepositoryService.getAllByCourseSeq(seq);
			
			
			for(KitInfo info : tempListKitInfo)
			{
				Optional<Kit> kit = kitRepositoryService.findBySeq(info.getKitSeq());
				
				if(kit.isPresent() == true)
					listKit.add(kit.get());
			}
			
			if(seq>=0){
				model.addAttribute("courseSeq", seq);	
			}		
			
			model.addAttribute("courseLevelList", courseLevelList);
			model.addAttribute("offlineStudyInfoData", offlineStudyInfoData);
			model.addAttribute("listKit", listKit);
			model.addAttribute("pageName", "listCourseInfo");
			
			return "admin/courseManage";
		}catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addAttribute("error","과목정보를 불러오는중 오류가 발생했습니다.");
			return "redirect:admin/errorPage";
		}

	}

	@RequestMapping("/admin/regCourse")
	public String regCourse(HttpSession session, Member member, MultipartHttpServletRequest mhsq, ReqCourse courseInfo, String kitList, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("regCourse");
		
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		
		Course tempCourse = new Course();
				
		try
		{							
			tempCourse = courseRepositoryService.AddCourse(courseInfo.convertToCourse());
			
			File dir = new File(rootPath + TathinkApplication.courseImageRealPath + tempCourse.getSeq() );
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
	        List<MultipartFile> mf = mhsq.getFiles("IMG_FILE1");   
	        
	        if (mf.size() > 0)	 
	        {
	            if( (mf.get(0).getSize() != 0) && (!mf.get(0).getOriginalFilename().equals("")) )
	            {
	            	mf.get(0).transferTo(new File(rootPath + TathinkApplication.courseImageRealPath + tempCourse.getSeq() + "/" + mf.get(0).getOriginalFilename()));
	            	
	            	tempCourse.setBigImgPath(TathinkApplication.courseImageUrl + tempCourse.getSeq() + "/" + mf.get(0).getOriginalFilename());
	            }
	        }
	        
	        mf = mhsq.getFiles("IMG_FILE2");   
	        
	        if (mf.size() > 0)	 
	        {
	            if( (mf.get(0).getSize() != 0) && (!mf.get(0).getOriginalFilename().equals("")) )
	            {
	            	mf.get(0).transferTo(new File(rootPath + TathinkApplication.courseImageRealPath + tempCourse.getSeq() + "/" + mf.get(0).getOriginalFilename()));
	            	
	            	tempCourse.setSmallImgPath(TathinkApplication.courseImageUrl + tempCourse.getSeq() + "/" + mf.get(0).getOriginalFilename());
	            }
	        }
	        
	        courseRepositoryService.AddCourse(tempCourse);
	        
	        kitInfoRepositoryService.AddKitInfoList(tempCourse.getSeq(), kitList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","과목 등록중 오류가 발생하였습니다.(1)");
			return "redirect:join/errorPage";
		}
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listCourseInfo");
		
		return "redirect:/admin/listCourseInfo";
	}
	
	@RequestMapping("/admin/kitManage")
	public String kitManage(Member member, String kitSeq, Model model, RedirectAttributes redirectAttributes)
	{	
		int seq = -1;
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		
		if(kitSeq==null || kitSeq==""){
			System.out.println("kitManage");	
		}
		else{
			System.out.println("kitManage seq=" + kitSeq);
			try{
				seq = Integer.parseInt(kitSeq);
			}
			catch(Exception e){
				e.printStackTrace();
				redirectAttributes.addAttribute("error","교보재정보를 불러오는중 오류가 발생했습니다.");
				return "redirect:admin/errorPage";
			}
		}
			
		if(seq>=0){
			model.addAttribute("kitSeq", seq);	
		}			
		model.addAttribute("pageName", "listKitInfo");
		
		return "admin/kitManage";
	}

	@RequestMapping("/admin/regKit")
	public String regKit(HttpSession session, Member member, MultipartHttpServletRequest mhsq, ReqKit kit, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/admin/regKit");
		
		String rootPath = MyFileUtil.getInstance(request).getRootPath();//request.getSession().getServletContext().getRealPath("/");
		
		Kit tempKit = new Kit();
				
		try
		{							
			tempKit = kitRepositoryService.AddKit(kit.convertToKit());
			
			tempKit.setUsername(member.getUsername());
			
			File dir = new File(rootPath + TathinkApplication.kitImageRealPath + tempKit.getSeq() );
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			for(int i =1 ; i<=5; i++){
		        List<MultipartFile> mf = mhsq.getFiles("IMG_FILE"+i);   
		        
		        if (mf.size() > 0)	 
		        {
		            if( (mf.get(0).getSize() != 0) && (!mf.get(0).getOriginalFilename().equals("")) )
		            {
		            	mf.get(0).transferTo(new File(rootPath + TathinkApplication.kitImageRealPath + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename()));
		            	
		            	switch(i){
		            	case 1:tempKit.setKitImg01Path(TathinkApplication.kitImageUrl + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename());break;
		            	case 2:tempKit.setKitImg02Path(TathinkApplication.kitImageUrl + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename());break;
		            	case 3:tempKit.setKitImg03Path(TathinkApplication.kitImageUrl + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename());break;
		            	case 4:tempKit.setKitImg04Path(TathinkApplication.kitImageUrl + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename());break;
		            	case 5:tempKit.setKitImg05Path(TathinkApplication.kitImageUrl + tempKit.getSeq() + "/" + mf.get(0).getOriginalFilename());break;
		            	}
		            	
		            }
		        }
			}
	        kitRepositoryService.AddKit(tempKit);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","과목 등록중 오류가 발생하였습니다.(1)");
			return "redirect:admin/errorPage";
		}
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listKitInfo");
		
		return "redirect:/admin/listKitInfo";
	}
	
	@RequestMapping("/admin/classManage")
	public String classManage(Member member, String classSeq, Model model, RedirectAttributes redirectAttributes)
	{				
		int seq = -1;
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		
		if(classSeq == null || classSeq==""){
			System.out.println("classManage");	
		}
		else{
			System.out.println("classManage seq=" + classSeq);
			try{
				seq = Integer.parseInt(classSeq);
			}
			catch(Exception e){
				e.printStackTrace();
				redirectAttributes.addAttribute("error","강좌정보를 불러오는중 오류가 발생했습니다.");
				return "redirect:admin/errorPage";
			}
		}
			
		if(seq>=0){
			model.addAttribute("classSeq", seq);	
		}			
		model.addAttribute("pageName", "listClassInfo");
		
		return "admin/classManage";
	}
		                        	
	@RequestMapping("/admin/regClass")
	public String regClass(Member member, ReqOfflineClass classInfo, OfflineLessonList list, Model model, RedirectAttributes redirectAttributes) 
	{	
		
		System.out.println("regClass");
			
		OfflineStudyInfo tempStudyInfo = new OfflineStudyInfo();
		OfflineClass tempClassInfo = new OfflineClass();
		
		try
		{										
			tempStudyInfo = classInfo.convertToStudyInfo();
					
			if(classInfo.getOfflineStudyInfoSeq() > 0){
				tempStudyInfo.setSeq(classInfo.getOfflineStudyInfoSeq());
			}		
			
			OfflineStudyInfo studyInfo = offlineStudyInfoRepositoryService.saveOfflineStudyInfo(tempStudyInfo);
			
			tempClassInfo = classInfo.convertToOfflineClass();
			
			tempClassInfo.setOfflineStudyInfoSeq(studyInfo.getSeq());
			
			OfflineClass tempClass = offlineClassRepositoryService.saveOfflinClass(tempClassInfo);
			
			offlineLessonRepositoryService.saveOfflineLesson(tempClass.getSeq(), list);
	        
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","강좌 등록중 오류가 발생하였습니다.(1)");
			return "redirect:admin/errorPage";
		}
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listClassInfo");
		
		return "redirect:/admin/listClassInfo";
	}
	
	@RequestMapping("/admin/listClassApply")
	public String classApplyInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/admin/listClassApply");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "listClassApply");
		
		return "admin/listClassApply";
	}
	
	@RequestMapping("/admin/listClassMember")
	public String classMemberInfo(Member member, String classSeq, String className, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/admin/listClassMember");
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		
		if(classSeq == null || classSeq==""){
			redirectAttributes.addAttribute("error","강좌정보가 없습니다.");
			return "redirect:admin/errorPage";
		}

		int seq = -1;
		System.out.println("listClassMember classSeq=" + classSeq);
		try{
			seq = Integer.parseInt(classSeq);
		}
		catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addAttribute("error","강좌정보를 불러오는중 오류가 발생했습니다.");
			return "redirect:admin/errorPage";
		}
						
		if(seq>=0){
			model.addAttribute("classSeq", seq);
			model.addAttribute("className", className);
			model.addAttribute("pageName", "listClassApply");
			
			return "admin/listClassMember";
		}else{
			redirectAttributes.addAttribute("error","해당 강좌정보가 없습니다.");
			return "redirect:admin/errorPage";
		}
		
	}
}
