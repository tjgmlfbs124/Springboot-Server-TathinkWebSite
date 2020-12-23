package com.tathink.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import com.tathink.controller.model.ReqClassFileWrite;
import com.tathink.controller.model.ReqClassHomeworkWrite;
import com.tathink.controller.model.ReqClassNoticeWrite;
import com.tathink.controller.model.ReqClassQnaWrite;
import com.tathink.controller.model.ReqClassReportWrite;
import com.tathink.controller.model.ReqMember;
import com.tathink.database.model.ClassAttendanceList;
import com.tathink.database.model.ClassFile;
import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.ClassNotice;
import com.tathink.database.model.ClassReport;
import com.tathink.database.model.Course;
import com.tathink.database.model.Member;
import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineClassMember;
import com.tathink.database.model.OfflineLesson;
import com.tathink.database.repository.ClassAttendanceRepositoryService;
import com.tathink.database.repository.ClassFileRepositoryService;
import com.tathink.database.repository.ClassHomeworkRepositoryService;
import com.tathink.database.repository.ClassNoticeRepositoryService;
import com.tathink.database.repository.ClassQnaRepositoryService;
import com.tathink.database.repository.ClassReportRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.OfflineClassMemberRepositoryService;
import com.tathink.database.repository.OfflineClassRepositoryService;
import com.tathink.database.repository.OfflineLessonRepositoryService;
import com.tathink.util.MyFileUtil;

@Controller
public class MyRoomController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@Autowired
	OfflineClassMemberRepositoryService offlineClassMemberRepositoryService;
	
	@Autowired
	OfflineClassRepositoryService offlineClassRepositoryService;
	
	@Autowired
	OfflineLessonRepositoryService offlineLessonRepositoryService;
	
	@Autowired
	CourseRepositoryService courseRepositoryService;
	
	@Autowired
	ClassNoticeRepositoryService classNoticeRepositoryService;
	
	@Autowired
	ClassFileRepositoryService classFileRepositoryService;

	@Autowired
	ClassHomeworkRepositoryService classHomeworkRepositoryService;

	@Autowired
	ClassReportRepositoryService classReportRepositoryService;
	
	@Autowired
	ClassQnaRepositoryService classQnaRepositoryService;
	
	@Autowired
	ClassAttendanceRepositoryService classAttendanceRepositoryService;
		
	@RequestMapping("/myroom/memberModify")
	public String memberInfo(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/memberModify");

		if(member == null )
		{
			redirectAttributes.addAttribute("error","해당 메뉴에 대한 권한이 없습니다.");
			return "redirect:/admin/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "memberModify");
		
		return "/myroom/memberModify";
	}
	
	@RequestMapping("/myroom/modifySubmit")
	public String modifySubmit(HttpSession session, Member member, MultipartHttpServletRequest mhsq, ReqMember memberInfo, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/modifySubmit");
		
		String rootPath = MyFileUtil.getInstance(request).getRootPath();//request.getSession().getServletContext().getRealPath("/");
		
		try
		{		
			String authConfirm = (String)session.getAttribute("EmailAuthConfirmForRegMember");
			session.setAttribute("EmailAuthConfirmForRegMember", "");
			
			if(authConfirm == null || authConfirm.equals("false"))
			{
				if(!member.getEmail().toString().equals(memberInfo.getEmail().toString())){
					redirectAttributes.addAttribute("error","이메일 인증이 되지 않았습니다.");
					return "redirect:myroom/errorPage";					
				}
			}
								
			File dir = new File(rootPath + TathinkApplication.memberImageRealPath + member.getUsername());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
	        List<MultipartFile> mf = mhsq.getFiles("IMG_FILE");   
	        
	        if (mf.size() > 0)	 
	        {
	            if( (mf.get(0).getSize() != 0) && (!mf.get(0).getOriginalFilename().equals("")) )
	            {
	            	mf.get(0).transferTo(new File(rootPath + TathinkApplication.memberImageRealPath + memberInfo.getUsername() + "/" + mf.get(0).getOriginalFilename()));
	            	
	            	memberInfo.setImgPath(TathinkApplication.memberImageUrl + memberInfo.getUsername() + "/" + mf.get(0).getOriginalFilename());
	            }
	        }	        
	        
	        memberRepositoryService.UpdateMember(memberInfo.UpdateToMember(member));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","회원 정보 수정중 오류가 발생하였습니다.(2)");
			return "redirect:myroom/errorPage";
		}
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "modifySubmit");
		
		return "/myroom/memberModifyResult";
	}
	
	@RequestMapping("/myroom/myclass")
	public String myclass(Member member, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/myclass");
		
		if(member == null){
			redirectAttributes.addAttribute("error","해당 메뉴에 대한 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "myclass");
		
		return "/myroom/myclass";			
	}
	
	@RequestMapping("/myroom/classroom")
	public String classRoom(Member member, Model model, int classSeq, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/classroom");
				
		if(member == null){
			redirectAttributes.addAttribute("error","해당 메뉴에 대한 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		List<OfflineClass> listClass = null;
		List<OfflineClassMember> tempClassMember = new ArrayList<OfflineClassMember>();
		List<Integer> listClassSeq = new ArrayList<Integer>();
		
		OfflineClass tempClass = offlineClassRepositoryService.getClass(classSeq);
		Course tempCourse = courseRepositoryService.getCourse(tempClass.getCourseSeq());	
		
		if(member == null || member.getLevel().equals("LEVEL_TEACHER")){
			listClass = offlineClassRepositoryService.getClassListByTeacherUsername(false, tempClass.getTeacherUsername(), new Sort(Direction.DESC, "regDate"));
		}else if(member == null || member.getLevel().equals("LEVEL_MEMBER")){
			
			tempClassMember = offlineClassMemberRepositoryService.getClassSeqByUserName(member.getUsername());
			
			if(tempClassMember.size()>0){
				for(OfflineClassMember classMember : tempClassMember){
					listClassSeq.add(classMember.getClassSeq());
				}
				
				listClass = offlineClassRepositoryService.getClassListBySeqList(false, listClassSeq, new Sort(Direction.DESC, "regDate"));
			}
		}
		
		List<OfflineLesson> listOfflineLesson = offlineLessonRepositoryService.getAllByOfflineClassSeq(classSeq, new Sort(Direction.ASC, "lessonOrder"));
		
				
		templeteSetter.setHeader(member, model);
		model.addAttribute("class", tempClass);
		model.addAttribute("course", tempCourse);
		model.addAttribute("listClass", listClass);
		model.addAttribute("listLesson", listOfflineLesson);
		model.addAttribute("pageName", "myclass");
		
		return "/myroom/classroom";			
	}
	
	@RequestMapping("/myroom/noticeWriteSubmit")
	public String noticeWriteSubmit(Member member, MultipartHttpServletRequest mhsq, ReqClassNoticeWrite reqNoticeWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/noticeWriteSubmit");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassNotice notice = null;
		reqNoticeWrite.setMhsq(mhsq);
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		try
		{
			notice = classNoticeRepositoryService.saveNotice(reqNoticeWrite, member);
			
			if(reqNoticeWrite.getDelFiles() != null && reqNoticeWrite.getDelFiles().length() > 0)
			{
				String[] delFiles = reqNoticeWrite.getDelFiles().split(";");
				
				for(String delFile : delFiles)
				{
					File file = new File(rootPath+delFile);
					if(file.exists() == true)
						file.delete();
				}
			}
			
			File dir = new File(rootPath + TathinkApplication.classNoticeFileRealPath + notice.getSeq());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			List<MultipartFile> listMf = reqNoticeWrite.getMhsq().getFiles("attachFiles");   
	        
			for(MultipartFile mf : listMf)
			{
				if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
	            {
					mf.transferTo(new File(rootPath + TathinkApplication.classNoticeFileRealPath + notice.getSeq() + "/" + mf.getOriginalFilename()));
	            }
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","공지사항 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		
		return "redirect:/myroom/classroom?classSeq="+reqNoticeWrite.getClassSeq();
	}
	
	@RequestMapping("/myroom/fileWriteSubmit")
	public String fileWriteSubmit(Member member, MultipartHttpServletRequest mhsq, ReqClassFileWrite reqFileWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/fileWriteSubmit");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassFile tempFile = null;
		reqFileWrite.setMhsq(mhsq);
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		try
		{
			tempFile = classFileRepositoryService.saveFile(reqFileWrite, member);
			
			if(reqFileWrite.getDelFiles() != null && reqFileWrite.getDelFiles().length() > 0)
			{
				String[] delFiles = reqFileWrite.getDelFiles().split(";");
				
				for(String delFile : delFiles)
				{
					File file = new File(rootPath+delFile);
					if(file.exists() == true)
						file.delete();
				}
			}
			
			File dir = new File(rootPath + TathinkApplication.classFileFileRealPath + tempFile.getSeq());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			List<MultipartFile> listMf = reqFileWrite.getMhsq().getFiles("attachFiles");   
	        
			for(MultipartFile mf : listMf)
			{
				if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
	            {
					mf.transferTo(new File(rootPath + TathinkApplication.classFileFileRealPath + tempFile.getSeq() + "/" + mf.getOriginalFilename()));
	            }
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","공지사항 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		
		return "redirect:/myroom/classroom?classSeq="+reqFileWrite.getClassSeq();
	}
		
	@RequestMapping("/myroom/homeworkWriteSubmit")
	public String homeworkWriteSubmit(Member member, MultipartHttpServletRequest mhsq, ReqClassHomeworkWrite reqHomeworkWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/homeworkWriteSubmit");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassHomework tempHomework = null;
		reqHomeworkWrite.setMhsq(mhsq);
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		try
		{
			tempHomework = classHomeworkRepositoryService.saveHomework(reqHomeworkWrite, member);
			
			if(reqHomeworkWrite.getDelFiles() != null && reqHomeworkWrite.getDelFiles().length() > 0)
			{
				String[] delFiles = reqHomeworkWrite.getDelFiles().split(";");
				
				for(String delFile : delFiles)
				{
					File file = new File(rootPath+delFile);
					if(file.exists() == true)
						file.delete();
				}
			}
			
			File dir = new File(rootPath + TathinkApplication.classHomeworkFileRealPath + tempHomework.getSeq());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			List<MultipartFile> listMf = reqHomeworkWrite.getMhsq().getFiles("attachFiles");   
	        
			for(MultipartFile mf : listMf)
			{
				if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
	            {
					mf.transferTo(new File(rootPath + TathinkApplication.classHomeworkFileRealPath + tempHomework.getSeq() + "/" + mf.getOriginalFilename()));
	            }
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","공지사항 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		
		return "redirect:/myroom/classroom?classSeq="+reqHomeworkWrite.getClassSeq();
	}
	
	@RequestMapping("/myroom/reportWriteSubmit")
	public String reportWriteSubmit(Member member, MultipartHttpServletRequest mhsq, ReqClassReportWrite reqReportWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/reportWriteSubmit");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassReport tempReport = null;
		reqReportWrite.setMhsq(mhsq);
		
		if(member == null || (!member.getLevel().equals("LEVEL_MEMBER")))
		{
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		try
		{
			tempReport = classReportRepositoryService.saveReport(reqReportWrite, member);
			
			if(reqReportWrite.getDelFiles() != null && reqReportWrite.getDelFiles().length() > 0)
			{
				String[] delFiles = reqReportWrite.getDelFiles().split(";");
				
				for(String delFile : delFiles)
				{
					File file = new File(rootPath+delFile);
					if(file.exists() == true)
						file.delete();
				}
			}
			
			File dir = new File(rootPath + TathinkApplication.classReportFileRealPath + tempReport.getSeq());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			List<MultipartFile> listMf = reqReportWrite.getMhsq().getFiles("attachFiles");   
	        
			for(MultipartFile mf : listMf)
			{
				if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
	            {
					mf.transferTo(new File(rootPath + TathinkApplication.classReportFileRealPath + tempReport.getSeq() + "/" + mf.getOriginalFilename()));
	            }
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","과제 제출중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		
		return "redirect:/myroom/classroom?classSeq="+reqReportWrite.getClassSeq();
	}
	
	@RequestMapping("/myroom/classQnaWrite")
	public String classQnaWrite(Member member, ReqClassQnaWrite reqWriteQna, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/classQnaWrite");
		
		if(member == null || member.getLevel().equals("LEVEL_TEACHER") || member.getLevel().equals("LEVEL_ADMIN"))
		{
			redirectAttributes.addAttribute("error","해당 게시글 작성 권한이 없습니다.");
			return "redirect:/board/errorPage";
		}
		
		try
		{
			classQnaRepositoryService.saveQuestion(Integer.parseInt(reqWriteQna.getSeq()), Integer.parseInt(reqWriteQna.getClassSeq()), reqWriteQna.getqTitle(), reqWriteQna.getqContent(), reqWriteQna.isbSec(), member);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","qna 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		
		return "redirect:/myroom/classroom?classSeq="+reqWriteQna.getClassSeq();
	}
	
	@RequestMapping("/myroom/classQnaAnswer")
	public String classQnaAnswer(Member member, ReqClassQnaWrite reqWriteQna, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("/myroom/classQnaAnswer");
		
		if(member == null || member.getLevel().equals("LEVEL_MEMBER"))
		{
			redirectAttributes.addAttribute("error","답변 권한이 없습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		try
		{
			classQnaRepositoryService.saveAnswer(Integer.parseInt(reqWriteQna.getSeq()), reqWriteQna.getaContent(), member);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","답변 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		
		return "redirect:/myroom/classroom?classSeq="+reqWriteQna.getClassSeq();
	}
	
	@RequestMapping("/myroom/classAttendanceSubmit")
	public String classAttendanceSubmit(Member member, MultipartHttpServletRequest mhsq, String classSeq, ClassAttendanceList checklist, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		System.out.println("/myroom/classAttendanceSubmit");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN"))){
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/myroom/errorPage";			
		}
		
		try
		{
			classAttendanceRepositoryService.savelist(checklist, member);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","출석부 저장중 오류가 발생하였습니다.");
			return "redirect:/myroom/errorPage";
		}
		return  "redirect:/myroom/classroom?classSeq="+checklist.getCheckList().get(0).getClassSeq();
	}
}
