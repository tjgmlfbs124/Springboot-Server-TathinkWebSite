package com.tathink.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tathink.TathinkApplication;
import com.tathink.controller.common.TempleteSetter;
import com.tathink.controller.model.ReqNoticeWrite;
import com.tathink.controller.model.ReqQnaWrite;
import com.tathink.controller.model.ReqQuestionGroupEduWrite;
import com.tathink.database.model.Member;
import com.tathink.database.model.Notice;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.NoticeRepositoryService;
import com.tathink.database.repository.QnaRepositoryService;
import com.tathink.database.repository.QuestionGroupEduRepositoryService;
import com.tathink.util.MyFileUtil;

@Controller
public class BoardController {
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
	
	@RequestMapping("/board/faq")
	public String faq(Member member, Model model)
	{	
		System.out.println("faq");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "faq");
		
		return "board/faq";
	}
	
	@RequestMapping("/board/notice")
	public String notice(Member member, String seq, Model model)
	{	
		System.out.println("notice");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "notice");
		model.addAttribute("seq", seq);
		
		return "board/notice";
	}
	
	@RequestMapping("/board/noticeWriteSubmit")
	public String noticeWriteSubmit(Member member, MultipartHttpServletRequest mhsq, ReqNoticeWrite reqNoticeWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("noticeWriteSubmit");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		Notice notice = null;
		reqNoticeWrite.setMhsq(mhsq);
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			redirectAttributes.addAttribute("error","해당 게시물 작성 권한이 없습니다.");
			return "redirect:/board/errorPage";
		}
		
		try
		{
			notice = noticeRepositoryService.saveNotice(reqNoticeWrite, member);
			
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
			
			File dir = new File(rootPath + TathinkApplication.noticeFileRealPath + notice.getSeq());
			
			if (!dir.isDirectory())
	            dir.mkdirs();
			
			List<MultipartFile> listMf = reqNoticeWrite.getMhsq().getFiles("attachFiles");   
	        
			for(MultipartFile mf : listMf)
			{
				if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
	            {
					mf.transferTo(new File(rootPath + TathinkApplication.noticeFileRealPath + notice.getSeq() + "/" + mf.getOriginalFilename()));
	            }
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","공지사항 저장중 오류가 발생하였습니다.");
			return "redirect:/board/errorPage";
		}
		
		
		return "redirect:/board/notice";
	}
	
	@RequestMapping("/board/qna")
	public String qna(Member member, String seq, Model model)
	{	
		System.out.println("qna");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "qna");
		model.addAttribute("seq", seq);
		
		return "board/qna";
	}
	
	@RequestMapping("/board/qnaWriteSubmit")
	public String qnaWriteSubmit(Member member, ReqQnaWrite reqWriteQna, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("qnaWriteSubmit");
		
		if(member == null || member.getLevel().equals("LEVEL_TEACHER") || member.getLevel().equals("LEVEL_ADMIN"))
		{
			redirectAttributes.addAttribute("error","해당 게시글 작성 권한이 없습니다.");
			return "redirect:/board/errorPage";
		}
		
		try
		{
			qnaRepositoryService.saveQuestion(Integer.parseInt(reqWriteQna.getSeq()), reqWriteQna.getqTitle(), reqWriteQna.getqContent(), reqWriteQna.isbSec(), member);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","qna 저장중 오류가 발생하였습니다.");
			return "redirect:/board/errorPage";
		}
		
		
		return "redirect:/board/qna";
	}
	
	@RequestMapping("/board/qnaAnswerSubmit")
	public String qnaAnswerSubmit(Member member, ReqQnaWrite reqWriteQna, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("qnaAnswerSubmit");
		
		if(member == null || member.getLevel().equals("LEVEL_MEMBER"))
		{
			redirectAttributes.addAttribute("error","답변 권한이 없습니다.");
			return "redirect:/board/errorPage";
		}
		
		try
		{
			qnaRepositoryService.saveAnswer(Integer.parseInt(reqWriteQna.getSeq()), reqWriteQna.getaContent(), member);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","답변 저장중 오류가 발생하였습니다.");
			return "redirect:/board/errorPage";
		}
		
		return "redirect:/board/qna";
	}
	
	@RequestMapping("/board/questionGroupEdu")
	public String questionGroupEdu(Member member, Model model)
	{	
		System.out.println("questionGroupEdu");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "questionGroupEdu");
		
		return "board/questionGroupEdu";
	}
	
	@RequestMapping("/board/questionGroupEduWriteSubmit")
	public String questionGroupEduWriteSubmit(Member member, ReqQuestionGroupEduWrite reqQuestionGroupEduWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("questionGroupEduWriteSubmit");

		try
		{
			questionGroupEduRepositoryService.save(
					-1,
					reqQuestionGroupEduWrite.getTitle(), 
					reqQuestionGroupEduWrite.getCompanyName(),
					reqQuestionGroupEduWrite.getCharder(),
					reqQuestionGroupEduWrite.getMobile01(),
					reqQuestionGroupEduWrite.getMobile02(),
					reqQuestionGroupEduWrite.getMobile03(),
					reqQuestionGroupEduWrite.getEmail01(),
					reqQuestionGroupEduWrite.getEmail02(),
					reqQuestionGroupEduWrite.getContent());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","저장중 오류가 발생하였습니다.");
			return "redirect:/board/errorPage";
		}
		
		return "redirect:/board/questionGroupEduSuccess";
	}
	
	@RequestMapping("/board/questionGroupEduSuccess")
	public String questionGroupEduSuccess(Member member, Model model)
	{	
		System.out.println("questionGroupEduSuccess");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "questionGroupEdu");
		
		return "board/questionGroupEduSuccess";
	}
}
