package com.tathink.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tathink.controller.model.ResFaqPage;
import com.tathink.controller.model.ResKit;
import com.tathink.controller.model.ResKitInfoPage;
import com.tathink.controller.model.ResMember;
import com.tathink.controller.model.ResMemberInfoPage;
import com.tathink.controller.model.ResTeacherPage;
import com.tathink.controller.model.ViewClassAttendance;
import com.tathink.TathinkApplication;
import com.tathink.controller.model.ReqClassFileWrite;
import com.tathink.controller.model.ReqClassNoticeWrite;
import com.tathink.controller.model.ReqFaqWrite;
import com.tathink.controller.model.ReqOfflineClassMember;
import com.tathink.controller.model.ResClassFile;
import com.tathink.controller.model.ResClassFilePage;
import com.tathink.controller.model.ResClassHomework;
import com.tathink.controller.model.ResClassHomeworkPage;
import com.tathink.controller.model.ResClassNotice;
import com.tathink.controller.model.ResClassNoticePage;
import com.tathink.controller.model.ResClassQna;
import com.tathink.controller.model.ResClassQnaPage;
import com.tathink.controller.model.ResClassReviewPage;
import com.tathink.controller.model.ResCourse;
import com.tathink.controller.model.ResCourseInfoPage;
import com.tathink.controller.model.ResNoticePage;
import com.tathink.controller.model.ResOfflineClass;
import com.tathink.controller.model.ResOfflineClassMemberPage;
import com.tathink.controller.model.ResOfflineClassPage;
import com.tathink.controller.model.ResNotice;
import com.tathink.controller.model.ResQna;
import com.tathink.controller.model.ResQnaPage;
import com.tathink.controller.model.ResQuestionGroupEdu;
import com.tathink.controller.model.ResQuestionGroupEduPage;
import com.tathink.database.model.ClassAttendance;
import com.tathink.database.model.ClassAttendanceList;
import com.tathink.database.model.ClassFile;
import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.ClassNotice;
import com.tathink.database.model.ClassQna;
import com.tathink.database.model.ClassReport;
import com.tathink.database.model.ClassReview;
import com.tathink.database.model.Course;
import com.tathink.database.model.Faq;
import com.tathink.database.model.Kit;
import com.tathink.database.model.Member;
import com.tathink.database.model.Notice;
import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineClassMember;
import com.tathink.database.model.OfflineLesson;
import com.tathink.database.model.Qna;
import com.tathink.database.model.QuestionGroupEdu;
import com.tathink.database.repository.ClassAttendanceRepositoryService;
import com.tathink.database.repository.ClassFileRepositoryService;
import com.tathink.database.repository.ClassHomeworkRepositoryService;
import com.tathink.database.repository.ClassNoticeRepositoryService;
import com.tathink.database.repository.ClassQnaRepositoryService;
import com.tathink.database.repository.ClassReportRepositoryService;
import com.tathink.database.repository.ClassReviewRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.FaqRepositoryService;
import com.tathink.database.repository.KitRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.database.repository.NoticeRepositoryService;
import com.tathink.database.repository.OfflineClassMemberRepositoryService;
import com.tathink.database.repository.OfflineClassRepositoryService;
import com.tathink.database.repository.OfflineLessonRepositoryService;
import com.tathink.database.repository.QnaRepositoryService;
import com.tathink.database.repository.QuestionGroupEduRepositoryService;
import com.tathink.util.MyFileUtil;
import com.tathink.utils.MailService;

@Controller
public class RestApiController {
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@Autowired
	FaqRepositoryService faqRepositoryService;
	
	@Autowired
	NoticeRepositoryService noticeRepositoryService;
	
	@Autowired
	QnaRepositoryService qnaRepositoryService;
	
	@Autowired
	QuestionGroupEduRepositoryService questionGroupEduRepositoryService;
	
	@Autowired
	CourseRepositoryService courseRepositoryService;
	
	@Autowired
	OfflineClassRepositoryService offlineClassRepositoryService;
	
	@Autowired
	OfflineClassMemberRepositoryService offlineClassMemberRepositoryService;
	
	@Autowired
	OfflineLessonRepositoryService offlineLessonRepositoryService;
	
	@Autowired
	KitRepositoryService kitRepositoryService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ClassNoticeRepositoryService classNoticeRepositoryService;
	
	@Autowired
	ClassFileRepositoryService classFileRepositoryService;	
	
	@Autowired
	ClassQnaRepositoryService classQnaRepositoryService;
	
	@Autowired
	ClassHomeworkRepositoryService classHomeworkRepositoryService;
	
	@Autowired
	ClassReportRepositoryService classReportRepositoryService;
	
	@Autowired
	ClassReviewRepositoryService classReviewRepositoryService;
	
	@Autowired
	ClassAttendanceRepositoryService classAttendanceRepositoryService;

		
	@RequestMapping("/api/idCheck")
	public @ResponseBody String idCheck(String strID)
	{	
		System.out.println("idCheck");
		
		try 
		{			
			Optional<Member> member = memberRepositoryService.findById(strID);			
			
			if(member.isPresent() == false)
				return "true";
			else
				return "false";
		} 
		catch (Exception e) 
		{
			return "아이디 체크 중 오류가 발생하였습니다.";
		}
	}
	
	@RequestMapping("/api/emailAuth")
	public @ResponseBody String emailAuth(HttpSession session, String email)
	{
		System.out.println("emailAuth");
		
		try
		{
			String title = "T.A. Think 메일 인증";
			String content = "인증 번호 : ";
			String randomNum = "";
			StringBuffer buffer = new StringBuffer();
			
			for(int i=0; i<=6; i++){
				int n = (int)(Math.random()*10);
				buffer.append(n);
			}
			
			content += buffer.toString();
			
			if (mailService.sendMail(email, title, content))
				randomNum = buffer.toString();	
			
			session.setAttribute("EmailAuthForRegMember", randomNum);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "인증번호 발송중 오류가 발생하였습니다.";
		}
		return "";
	}
	
	@RequestMapping("/api/emailAuthConfirm")
	public @ResponseBody String emailAuthConfirm(HttpSession session, String num)
	{
		System.out.println("emailAuthConfirm");
		
		try
		{
			String sentNum = (String)session.getAttribute("EmailAuthForRegMember");
			
			if(sentNum != null && sentNum.length() > 0 && sentNum.equals(num) )
			{
				session.setAttribute("EmailAuthConfirmForRegMember", "true");
			}
			else
			{
				session.setAttribute("EmailAuthConfirmForRegMember", "false");
				return "인증번호가 잘못 되었습니다.";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "인증 도중 서버에서 오류가 발생하였습니다.";
		}
		
		return "";
	}
	
	@RequestMapping("/api/reqEmailAuthForFindPwd")
	public @ResponseBody String reqEmailAuthForFindPwd(HttpSession session, String id ,String name, String email)
	{
		System.out.println("reqEmailAuthForFindPwd");

		Optional<Member> member = null;
		
		StringBuffer buffer = new StringBuffer();
		
		try
		{
			member = memberRepositoryService.findById(id);
			
			if(member.isPresent() == false)
			{
				return "존재하지 않는 사용자 입니다.";
			}
			
			if(member.get().getRealname().equals(name) == false || member.get().getEmail().equals(email) == false)
			{
				return "존재하지 않는 사용자 입니다.";
			}
			
			for(int i=0; i<=6; i++)
			{
				int n = (int)(Math.random()*10);
				buffer.append(n);
			}
					
			if (mailService.sendMail(email, "T.A. Think 메일 인증", "인증 번호 : " + buffer.toString()))
			{
				session.setAttribute("EmailAuthForFindPwd", buffer.toString());
				return "";
			}
			else
			{
				return "인증번호 전송에 실패 하였습니다.(1)";
			}
		}
		catch(Exception e)
		{
			return "인증번호 전송에 실패 하였습니다.(2)";
		}
	}
	
	@RequestMapping("/api/listTeacher")
	public @ResponseBody ResTeacherPage listTeacher(String option ,String word, String pageIdx)
	{
		System.out.println("api/listTeacher");

		try
		{
			Page<Member> member = null;
			
			if(option.equals("NAME"))
				member = memberRepositoryService.getAllByLevelRealNameBDeletePageable(false, "LEVEL_TEACHER", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname", "username", "regDate")));
			else
				member = memberRepositoryService.getAllByLevelUserNameBDeletePageable(false, "LEVEL_TEACHER", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname", "username", "regDate")));
			
			return new ResTeacherPage(member);
		}
		catch(Exception e)
		{
			return new ResTeacherPage("선생님 정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	

	@RequestMapping("/api/listNotice")
	public @ResponseBody ResNoticePage listNotice(String option, String word, String pageIdx)
	{
		System.out.println("api/listNotice");

		try
		{
			Page<Notice> notice = null;
	
			if(option.equals("ALL"))
			{
				notice = noticeRepositoryService.getAllByTitleContentPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				notice = noticeRepositoryService.getAllByTitlePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CONTENT"))
			{
				notice = noticeRepositoryService.getAllByContentPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			return new ResNoticePage(notice);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResNoticePage("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewNotice")
	public @ResponseBody ResNotice viewNotice(Member member, String seq)
	{
		System.out.println("api/viewNotice");

		try
		{		
			Notice notice = null;
			notice = noticeRepositoryService.findBySeq(Integer.parseInt(seq));
		
			return new ResNotice(notice);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResNotice("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteListNotice")
	public @ResponseBody ResNoticePage deleteListNotice(Member member, String listSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteListNotice");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResNoticePage("해당 게시길 삭제 권한이 없습니다.");
		
			noticeRepositoryService.removeByListNoticeSeq(listSeq.split(":"));
		
			return listNotice(option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResNoticePage("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteNotice")
	public @ResponseBody ResNoticePage deleteNotice(Member member, String seq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteNotice");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResNoticePage("해당 게시길 삭제 권한이 없습니다.");
		
			noticeRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return listNotice(option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResNoticePage("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/downloadNoticeFile")
	public void downloadNoticeFile(Member member, String filePath, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadNoticeFile");
 		
		filePath = MyFileUtil.getInstance(request).getRootPath() + filePath;

		int idx = filePath.lastIndexOf("/");
		MyFileUtil.getInstance(request).fileOut( request, response, filePath, filePath.substring(idx+1));
		
	}
	
	@RequestMapping("/api/listFaq")
	public @ResponseBody ResFaqPage listFaq(String word, String pageIdx)
	{
		System.out.println("api/listFaq");

		Page<Faq> faq = null;

		try
		{
			faq = faqRepositoryService.getAllByKeyWordPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResFaqPage("FAQ리스트를 읽어오는 도중 오류가 발생하였습니다.");
			
		}
		return new ResFaqPage(faq);
	}
	
	@RequestMapping("/api/deleteFaq")
	public @ResponseBody ResFaqPage deleteFaq(Member member, String listSeq, String word, String pageIdx)
	{
		System.out.println("api/deleteFaq");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
			return new ResFaqPage("게시글을 삭제할 권한이 없습니다.");
		
		try
		{
			faqRepositoryService.removeByListFaqSeq(listSeq.split(":"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResFaqPage("게시글을 삭제하는 도중 오류가 발생하였습니다.");
		}
		
		return listFaq(word,pageIdx);
	}
	
	@RequestMapping("/api/writeFaq")
	public @ResponseBody ResFaqPage writeFaq(Member member, ReqFaqWrite reqFaqWrite, String word, String pageIdx)
	{
		System.out.println("api/writeFaq");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
			return new ResFaqPage("게시글 작성 권한이 없습니다.");
		
		try
		{
			faqRepositoryService.saveFaq(reqFaqWrite.getSeq(), reqFaqWrite.getqContent(), reqFaqWrite.getaContent());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResFaqPage("게시글을 저장하는 도중 오류가 발생하였습니다.");
		}
		return listFaq(word,pageIdx);
	}
	
	@RequestMapping("/api/listQna")
	public @ResponseBody ResQnaPage listQna(String option, String word, String pageIdx)
	{
		System.out.println("api/listQna");

		Page<Qna> qna = null;

		try
		{
			if(option.equals("ALL"))
			{
				qna = qnaRepositoryService.getAllByQTitleQuestionAnswerQWriterStatePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				qna = qnaRepositoryService.getAllByQTitlePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("QUESTION"))
			{
				qna = qnaRepositoryService.getAllByQuestionPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("ANSWER"))
			{
				qna = qnaRepositoryService.getAllByAnswerPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("WRITER"))
			{
				qna = qnaRepositoryService.getAllByQWriterPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("ANSSTATE"))
			{
				qna = qnaRepositoryService.getAllByStatePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			return new ResQnaPage(qna);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQnaPage("QnA 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewQna")
	public @ResponseBody ResQna viewQna(Member member, String seq)
	{
		System.out.println("api/viewQna");

		Qna qna = null;

		try
		{
			qna = qnaRepositoryService.findBySeq(Integer.parseInt(seq));
			
			if(qna.isbSec() == false)
			{
				return new ResQna(qna, member);
			}
			else if(member != null && (qna.getqWriter().getUsername().equals(member.getUsername()) || member.getLevel().equals("LEVEL_TEACHER") || member.getLevel().equals("LEVEL_ADMIN")))
			{
				return new ResQna(qna, member);
			}
			else
			{
				return new ResQna("해당 게시물에 권한이 없습니다.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQna("QnA 데이터를 읽어오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteQna")
	public @ResponseBody ResQnaPage deleteQna(Member member, String seq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteQna");
		
		try
		{
			Qna qna = qnaRepositoryService.findBySeq(Integer.parseInt(seq));
			
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN") && !qna.getqWriter().getUsername().equals(member.getUsername())))
				return new ResQnaPage("해당 게시물 삭제 권한이 없습니다.");
			
			qnaRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return listQna(option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQnaPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/deleteListQna")
	public @ResponseBody ResQnaPage deleteListQna(Member member, String listSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteListQna");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQnaPage("해당 게시글을 삭제할 권한이 없습니다.");
			
			qnaRepositoryService.removeByListQnaSeq(listSeq.split(":"));
		
			return listQna(option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQnaPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listQuestionGroupEdu")
	public @ResponseBody ResQuestionGroupEduPage listQuestionGroupEdu(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listQuestionGroupEdu");

		Page<QuestionGroupEdu> questionGroupEduPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQuestionGroupEduPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			if(option.equals("ALL"))
			{
				questionGroupEduPage = questionGroupEduRepositoryService.getAllByTitleStatePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				questionGroupEduPage = questionGroupEduRepositoryService.getAllByTitlePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CONTENT"))
			{
				questionGroupEduPage = questionGroupEduRepositoryService.getAllByContentPageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("ANSSTATE"))
			{
				questionGroupEduPage = questionGroupEduRepositoryService.getAllByStatePageable(word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			return new ResQuestionGroupEduPage(questionGroupEduPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQuestionGroupEduPage("'단체교육 문의' 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewQuestionGroupEdu")
	public @ResponseBody ResQuestionGroupEdu viewQuestionGroupEdu(Member member, String seq)
	{
		System.out.println("api/viewQuestionGroupEdu");

		QuestionGroupEdu questionGroupEdu = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQuestionGroupEdu("해당 게시글에 대한 권한이 없습니다.");
			
			questionGroupEdu = questionGroupEduRepositoryService.findBySeq(Integer.parseInt(seq));
			
			return new ResQuestionGroupEdu(questionGroupEdu);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQuestionGroupEdu("'단체교육 문의' 데이터를 읽어오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteListQuestionGroupEdu")
	public @ResponseBody ResQuestionGroupEduPage deleteListQuestionGroupEdu(Member member, String listSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteListQuestionGroupEdu");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQuestionGroupEduPage("해당 게시물 삭제 권한이 없습니다.");
			
			questionGroupEduRepositoryService.removeByListQuestoinGroupEduSeq(listSeq.split(":"));
		
			return listQuestionGroupEdu(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQuestionGroupEduPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteQuestionGroupEdu")
	public @ResponseBody ResQuestionGroupEduPage deleteQuestionGroupEdu(Member member, String seq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteQuestionGroupEdu");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQuestionGroupEduPage("해당 게시물 삭제 권한이 없습니다.");
			
			questionGroupEduRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return listQuestionGroupEdu(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQuestionGroupEduPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/answerQuestionGroupEdu")
	public @ResponseBody ResQuestionGroupEduPage answerQuestionGroupEdu(Member member, String seq, String option, String word, String pageIdx)
	{
		System.out.println("api/answerQuestionGroupEdu");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResQuestionGroupEduPage("해당 게시물 삭제 권한이 없습니다.");
			
			questionGroupEduRepositoryService.saveAnswer(Integer.parseInt(seq), true);
		
			return listQuestionGroupEdu(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResQuestionGroupEduPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	

	@RequestMapping("/api/listMemberInfo")
	public @ResponseBody ResMemberInfoPage listMembeInfo(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listMembeInfo");

		Page<Member> memberPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResMemberInfoPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			if(option.equals("ALL"))
			{
				memberPage = memberRepositoryService.getAllByUsernameRealNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("ID"))
			{
				memberPage = memberRepositoryService.getAllByUserNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("NAME"))
			{
				memberPage = memberRepositoryService.getAllByRealNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			return new ResMemberInfoPage(memberPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMemberInfoPage("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewMember")
	public @ResponseBody ResMember viewMember(Member member, String username)
	{
		System.out.println("api/viewMember");

		Member memberInfo = null;
		ResMember resMemberInfo = null;
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResMember("해당 회원에 대한 권한이 없습니다.");
			
			memberInfo = memberRepositoryService.findById(username).get();
			
			resMemberInfo = new ResMember(memberInfo);
			resMemberInfo.setAllInfo(memberInfo);
			
			return resMemberInfo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMember("회원정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	@RequestMapping("/api/viewMemberModify")
	public @ResponseBody ResMember viewMemberModify(Member member, String username)
	{
		System.out.println("api/viewMemberModify");

		Member memberInfo = null;
		ResMember resMemberInfo = null;
		
		try
		{
			if(member == null )
				return new ResMember("해당 회원정보가 없습니다.");
			
			memberInfo = memberRepositoryService.findById(username).get();
			
			resMemberInfo = new ResMember(memberInfo);
			resMemberInfo.setAllInfo(memberInfo);
			
			return resMemberInfo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMember("회원정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}	
	
	@RequestMapping("/api/modifyLevelListMember")
	public @ResponseBody ResMemberInfoPage modifyLevelListMember(Member member, String listUsername, String level, String option, String word, String pageIdx)
	{
		System.out.println("api/modifyLevelListMember");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResMemberInfoPage("해당 회원에 대한 권한이 없습니다.");
			
			memberRepositoryService.updateMemberLevel(listUsername,level);

			return listMembeInfo(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMemberInfoPage("회원정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listCourseInfo")
	public @ResponseBody ResCourseInfoPage listCourseInfo(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listCourseInfo");

		Page<Course> coursePage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResCourseInfoPage("해당 게시글리스트에 대한 권한이 없습니다.");

			if(option.equals("NAME"))
			{
				coursePage = courseRepositoryService.getAllByCourseNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CODE"))
			{
				coursePage = courseRepositoryService.getAllByCodeBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}			
			else if(option.equals("TEACHER"))
			{
				coursePage = courseRepositoryService.getAllByTeacherUserNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			return new ResCourseInfoPage(coursePage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResCourseInfoPage("과목정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/getCourseInfo")
	public @ResponseBody ResCourse getCourseInfo(Member member, int courseSeq)
	{
		System.out.println("api/getCourseInfo");

		Course courseInfo = null;
		ResCourse resCourseInfo = null;
		
		try
		{
			if( courseSeq < 0 )
				return new ResCourse("해당 과목정보가 없습니다.");
			
			courseInfo = courseRepositoryService.getCourse(courseSeq);
			resCourseInfo = new ResCourse(courseInfo);

			return resCourseInfo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResCourse("과목정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteCourseList")
	public @ResponseBody ResCourseInfoPage deleteCourseList(Member member, String listCourse, String level, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteCourseList");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResCourseInfoPage("해당 회원에 대한 권한이 없습니다.");
			
			courseRepositoryService.DeleteCourseList(listCourse);

			return listCourseInfo(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResCourseInfoPage("과목정보를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listKitInfo")
	public @ResponseBody ResKitInfoPage listKitInfo(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listKitInfo");

		Page<Kit> kitPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResKitInfoPage("해당 게시글리스트에 대한 권한이 없습니다.");

			if(option.equals("ALL"))
			{
				kitPage = kitRepositoryService.getAllByCodeNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CODE"))
			{
				kitPage = kitRepositoryService.getAllByCodeBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}			
			else if(option.equals("NAME"))
			{
				kitPage = kitRepositoryService.getAllByNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			return new ResKitInfoPage(kitPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResKitInfoPage("교보재 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/getKitInfo")
	public @ResponseBody ResKit getKitInfo(Member member, int kitSeq)
	{
		System.out.println("api/getKitInfo");

		Optional<Kit> kitInfo = null;
		ResKit resKitInfo = null;
		
		try
		{
			if( kitSeq < 0 )
				return new ResKit("해당 교보재정보가 없습니다.");
			
			kitInfo = kitRepositoryService.findBySeq(kitSeq);
			resKitInfo = new ResKit(kitInfo.get());

			return resKitInfo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResKit("교보재정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteKitList")
	public @ResponseBody ResKitInfoPage deleteKitList(Member member, String listKit, String level, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteKitList");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResKitInfoPage("해당 회원에 대한 권한이 없습니다.");
			
			kitRepositoryService.DeleteKitList(listKit);

			return listKitInfo(member, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResKitInfoPage("교보재정보를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listTeacherInfo")
	public @ResponseBody ResMemberInfoPage listTeacherInfo(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listTeacherInfo");

		Page<Member> memberPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResMemberInfoPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			if(option.equals("ALL")||option.equals("NAME"))
			{
				memberPage = memberRepositoryService.getAllByTeacherRealNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("ID"))
			{
				memberPage = memberRepositoryService.getAllByTeacherUserNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("NUMBER"))
			{
				memberPage = memberRepositoryService.getAllByTeacherNumberBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			return new ResMemberInfoPage(memberPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMemberInfoPage("강사 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listClassInfo")
	public @ResponseBody ResOfflineClassPage listClassInfo(Member member, String state, String type, String word, String pageIdx)
	{
		System.out.println("api/listClassInfo");

		Page<OfflineClass> offlineClassPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassPage("해당 게시글리스트에 대한 권한이 없습니다.");

			if(state.equals("ALL")) /*기간 - 전체*/
			{
				offlineClassPage = offlineClassRepositoryService.getAllClassByBDeletePageable(false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(state.equals("WAIT")) /*기간 - 대기(신청전)*/
			{
				offlineClassPage = offlineClassRepositoryService.getWaitClassByBDeletePageable(false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}			
			else if(state.equals("APPLY")) /*기간 - 신청중*/
			{
				offlineClassPage = offlineClassRepositoryService.getApplyClassByBDeletePageable(false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(state.equals("START")) /*기간 - 강의중*/
			{
				offlineClassPage = offlineClassRepositoryService.getStartClassByBDeletePageable(false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(state.equals("END")) /*기간 - 종강*/
			{
				offlineClassPage = offlineClassRepositoryService.getEndClassByBDeletePageable(false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}			
			return new ResOfflineClassPage(offlineClassPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassPage("과목정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/getClassInfo")
	public @ResponseBody ResOfflineClass getClassInfo(Member member, int classSeq)
	{
		System.out.println("api/getClassInfo");

		OfflineClass classInfo = null;
		ResOfflineClass resClassInfo = null;
		
		try
		{
			if( classSeq < 0 )
				return new ResOfflineClass("해당 과목정보가 없습니다.");
			
			classInfo = offlineClassRepositoryService.getClass(classSeq);
			
			resClassInfo = new ResOfflineClass(classInfo);

			return resClassInfo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClass("과목정보를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	
	@RequestMapping("/api/deleteClassList")
	public @ResponseBody ResOfflineClassPage deleteClassList(Member member, String listClass, String state, String type, String word, String pageIdx)
	{
		System.out.println("api/deleteCourseList");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassPage("해당 회원에 대한 권한이 없습니다.");
			
			offlineClassRepositoryService.deleteOfflinClass(listClass);

			return listClassInfo(member, state, type, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassPage("과목정보를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/getLessonInfo")
	public @ResponseBody List<OfflineLesson> getLessonInfo(Member member, int classSeq)
	{
		System.out.println("/api/getLessonInfo");

		List<OfflineLesson> listOfflineLesson = null;
		
		try
		{
			if( classSeq < 0 )
				return null;
			
			listOfflineLesson = offlineLessonRepositoryService.getAllByOfflineClassSeq(classSeq, new Sort(Direction.ASC, "lessonOrder"));
			
			return listOfflineLesson;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/api/deleteLessonList")
	public @ResponseBody List<OfflineLesson> deleteLessonList(Member member, String listLesson, int classSeq)
	{
		System.out.println("/api/deleteLessonList");

		List<OfflineLesson> listOfflineLesson = null;
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return null;
			
			offlineLessonRepositoryService.deleteLessonList(listLesson);

			if( classSeq < 0 )
				return null;
			
			listOfflineLesson = offlineLessonRepositoryService.getAllByOfflineClassSeq(classSeq, new Sort(Direction.ASC, "lessonOrder"));
			
			return listOfflineLesson;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/api/listClassMemberInfo")
	public @ResponseBody ResOfflineClassMemberPage listClassMembeInfo(Member member, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/listClassMemberInfo");

		Page<OfflineClassMember> memberPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassMemberPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			if(option.equals("ALL"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberRealNameUserNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			else if(option.equals("NAME"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberRealNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			else if(option.equals("ID"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberUserNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			else if(option.equals("NUMBER"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberMobileBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			return new ResOfflineClassMemberPage(memberPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listClassAddMember")
	public @ResponseBody ResMemberInfoPage listClassAddMember(Member member, String option, String word, String pageIdx)
	{
		System.out.println("api/listClassAddMember");

		Page<Member> memberPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResMemberInfoPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			if(option.equals("ALL")||option.equals("NAME"))
			{
				memberPage = memberRepositoryService.getAllByClassAddMemberRealNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("ID"))
			{
				memberPage = memberRepositoryService.getAllByClassAddMemberUserNameBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("NUMBER"))
			{
				memberPage = memberRepositoryService.getAllByClassAddMemberNumberBDeletePageable(false, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			return new ResMemberInfoPage(memberPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResMemberInfoPage("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	
	@RequestMapping("/api/reqClassAddMember")
	public @ResponseBody ResOfflineClassMemberPage reqClassAddMember(Member member,int classSeq, ReqOfflineClassMember reqMember, String option, String word, String pageIdx)
	{
		System.out.println("admin/reqClassAddMember");
		
		OfflineClassMember tempMember = null;
		try
		{							
			
			tempMember = offlineClassMemberRepositoryService.getByClassSeqUserName(classSeq, reqMember.getUsername());
			
			if(tempMember!=null){
				return new ResOfflineClassMemberPage("이미 수강중입니다.");
			}
	
			tempMember = reqMember.convertToOfflineClassMember();
			
			offlineClassMemberRepositoryService.saveOfflinClassMember(tempMember);
			
			return listClassMembeInfo(member,classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("수강신청취소를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/reqOfflineClassAddMember")
	public @ResponseBody ResOfflineClassMemberPage reqOfflineClassAddMember(Member member,int classSeq, ReqOfflineClassMember reqMember)
	{
		System.out.println("admin/reqOfflineClassAddMember");
		
		OfflineClassMember tempMember = null;
		try
		{							
		
			tempMember = offlineClassMemberRepositoryService.getByClassSeqUserName(classSeq, reqMember.getUsername());
			
			if(tempMember!=null){
				return new ResOfflineClassMemberPage("이미 수강중입니다.");
			}

			tempMember = reqMember.convertToOfflineClassMember();
			
			offlineClassMemberRepositoryService.saveOfflinClassMember(tempMember);
			
			return new ResOfflineClassMemberPage("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("수강신청취소를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassMember")
	public @ResponseBody ResOfflineClassMemberPage deleteClassMember(Member member, int classSeq, String listMember, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteCourseList");
		
		try
		{
			if( classSeq < 0 )
				return new ResOfflineClassMemberPage("수강신청취소 정보에 오류가 발생하였습니다.");
			
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassMemberPage("해당 회원에 대한 권한이 없습니다.");
			
			offlineClassMemberRepositoryService.deleteOfflinClass(classSeq, listMember);
			

			return listClassMembeInfo(member,classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("수강신청취소를 처리하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listMyClass")
	public @ResponseBody ResOfflineClassPage ListMyClass(Member member, String type, String word, String pageIdx)
	{	
		System.out.println("api/listMyClass");
		try{
			
			List<OfflineClassMember> tempClassMember = new ArrayList<OfflineClassMember>();
			List<Integer> listClassSeq = new ArrayList<Integer>();
			Page<OfflineClass> offlineClassPage = null;				
								
			if(member.getLevel().equals("LEVEL_MEMBER")){
				
				tempClassMember = offlineClassMemberRepositoryService.getClassSeqByUserName(member.getUsername(),new Sort(Direction.DESC, "regDate"));
				
				if(tempClassMember.size()<=0){
					return null;
				}
				
				for(OfflineClassMember classMember : tempClassMember){
						listClassSeq.add(classMember.getClassSeq());
				}
				if(type.equals("MYCLASS_ALL")){
					offlineClassPage = offlineClassRepositoryService.getAllMyClassByBDeletePageable(false, listClassSeq, "ALL", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}else if(type.equals("MYCLASS_APPLY")){
					offlineClassPage = offlineClassRepositoryService.getApplyMyClassByBDeletePageable(false, listClassSeq, "ALL", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}else if(type.equals("MYCLASS_START")){
					offlineClassPage = offlineClassRepositoryService.getStartMyClassByBDeletePageable(false, listClassSeq, "ALL", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}else if(type.equals("MYCLASS_END")){
					offlineClassPage = offlineClassRepositoryService.getEndMyClassByBDeletePageable(false, listClassSeq, "ALL", word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				
				if (offlineClassPage!=null){			
					return new ResOfflineClassPage(offlineClassPage);
				}else{
					return new ResOfflineClassPage("강좌정보를 불러오는 중 오류가 발생하였습니다.");
				}
				
			}else if(member.getLevel().equals("LEVEL_TEACHER")|| member.getLevel().equals("LEVEL_ADMIN")){
				
				if(type.equals("CLASS_APPLY")){
					offlineClassPage = offlineClassRepositoryService.getApplyTeacherClassByBDeletePageable(false, member.getUsername(), word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			    }else if(type.equals("CLASS_START")){
					offlineClassPage = offlineClassRepositoryService.getStartTeacherClassByBDeletePageable(false, member.getUsername(), word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}else if(type.equals("CLASS_END")){
					offlineClassPage = offlineClassRepositoryService.getEndTeacherClassByBDeletePageable(false, member.getUsername(), word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				
				if (offlineClassPage!=null){			
					return new ResOfflineClassPage(offlineClassPage);
				}else{
					return new ResOfflineClassPage("강좌정보를 불러오는 중 오류가 발생하였습니다.");
				}
			}else{
				return new ResOfflineClassPage("강좌정보를 불러오는 중 오류가 발생하였습니다.");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassPage("강좌정보를 불러오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/classNotice")
	public @ResponseBody ResClassNoticePage classNotice(String option, String word, int classSeq, String pageIdx)
	{
		System.out.println("api/classNotice");

		try
		{
			Page<ClassNotice> notice = null;
	
			if(option.equals("ALL"))
			{
				notice = classNoticeRepositoryService.getAllByTitleContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				notice = classNoticeRepositoryService.getAllByTitlePageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CONTENT"))
			{
				notice = classNoticeRepositoryService.getAllByContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			return new ResClassNoticePage(notice);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassNoticePage("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewClassNotice")
	public @ResponseBody ResClassNotice viewClassNotice(Member member, String seq)
	{
		System.out.println("api/viewClassNotice");

		try
		{		
			ClassNotice notice = null;
			notice = classNoticeRepositoryService.findBySeq(Integer.parseInt(seq));
		
			return new ResClassNotice(notice);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassNotice("공지사항리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/myroom/classNoticeWrite")
	public ResClassNoticePage classNoticeWrite(Member member, MultipartHttpServletRequest mhsq, ReqClassNoticeWrite reqNoticeWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("classNoticeWrite");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassNotice notice = null;
		reqNoticeWrite.setMhsq(mhsq);
		
		int classSeq = -1;
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			
			return new ResClassNoticePage("해당 게시물 작성 권한이 없습니다.");
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
			
			classSeq = Integer.parseInt(reqNoticeWrite.getClassSeq());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return new ResClassNoticePage("공지사항 저장중 오류가 발생하였습니다.");
		}
		
		return classNotice("ALL", "", classSeq, "0");
	}
	
	@RequestMapping("/api/deleteClassNoticeList")
	public @ResponseBody ResClassNoticePage deleteClassNoticeList(Member member, int classSeq, String listSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassNotice");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassNoticePage("해당 게시길 삭제 권한이 없습니다.");
		
			classNoticeRepositoryService.removeByListNoticeSeq(listSeq.split(":"));
		
			return classNotice(option, word, classSeq, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassNoticePage("공지사항을 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassNotice")
	public @ResponseBody ResClassNoticePage deleteClassNotice(Member member, String seq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassNotice");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassNoticePage("해당 게시길 삭제 권한이 없습니다.");
		
			classNoticeRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return classNotice(option, word, classSeq, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassNoticePage("공지사항을 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/downloadClassNotice")
	public void downloadClassNotice(Member member, String filePath, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadNoticeFile");
 		
		filePath = MyFileUtil.getInstance(request).getRootPath() + filePath;

		int idx = filePath.lastIndexOf("/");
		MyFileUtil.getInstance(request).fileOut( request, response, filePath, filePath.substring(idx+1));
		
	}
	
	@RequestMapping("/api/classFile")
	public @ResponseBody ResClassFilePage classFile(String option, String word, int classSeq, String pageIdx)
	{
		System.out.println("api/classFile");

		try
		{
			Page<ClassFile> tempFile = null;
	
			if(option.equals("ALL"))
			{
				tempFile = classFileRepositoryService.getAllByTitleContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				tempFile = classFileRepositoryService.getAllByTitlePageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CONTENT"))
			{
				tempFile = classFileRepositoryService.getAllByContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			return new ResClassFilePage(tempFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassFilePage("강좌자료실 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewClassFile")
	public @ResponseBody ResClassFile viewClassFile(Member member, String seq)
	{
		System.out.println("api/viewClassFile");

		try
		{		
			ClassFile tempFile = null;
			tempFile = classFileRepositoryService.findBySeq(Integer.parseInt(seq));
		
			return new ResClassFile(tempFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassFile("강좌자료실 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/myroom/classFileWrite")
	public ResClassFilePage classFileWrite(Member member, MultipartHttpServletRequest mhsq, ReqClassFileWrite reqFileWrite, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("classFileWrite");
		String rootPath = MyFileUtil.getInstance(request).getRootPath();
		ClassFile tempFile = null;
		reqFileWrite.setMhsq(mhsq);
		
		int classSeq = -1;
		
		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
		{
			
			return new ResClassFilePage("해당 게시물 작성 권한이 없습니다.");
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
			
			classSeq = Integer.parseInt(reqFileWrite.getClassSeq());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return new ResClassFilePage("강좌자료 저장중 오류가 발생하였습니다.");
		}
		
		return classFile("ALL", "", classSeq, "0");
	}
	
	@RequestMapping("/api/deleteClassFileList")
	public @ResponseBody ResClassFilePage deleteClassFileList(Member member, int classSeq, String listSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassFileList");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassFilePage("해당 게시길 삭제 권한이 없습니다.");
		
			classFileRepositoryService.removeByListFileSeq(listSeq.split(":"));
		
			return classFile(option, word, classSeq, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassFilePage("강좌자료를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassFile")
	public @ResponseBody ResClassFilePage deleteClassFile(Member member, String seq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassFile");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassFilePage("해당 게시길 삭제 권한이 없습니다.");
		
			classFileRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return classFile(option, word, classSeq, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassFilePage("강좌자료를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/downloadClassFile")
	public void downloadClassFile(Member member, String filePath, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadClassFile");
 		
		filePath = MyFileUtil.getInstance(request).getRootPath() + filePath;

		int idx = filePath.lastIndexOf("/");
		MyFileUtil.getInstance(request).fileOut( request, response, filePath, filePath.substring(idx+1));
		
	}
	
	@RequestMapping("/api/listClassQna")
	public @ResponseBody ResClassQnaPage listClassQna(int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/listClassQna");

		Page<ClassQna> qna = null;

		try
		{
			if(option.equals("ALL"))
			{
				qna = classQnaRepositoryService.getAllByQTitleQuestionAnswerQWriterStatePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				qna = classQnaRepositoryService.getAllByQTitlePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("QUESTION"))
			{
				qna = classQnaRepositoryService.getAllByQuestionPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("ANSWER"))
			{
				qna = classQnaRepositoryService.getAllByAnswerPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("WRITER"))
			{
				qna = classQnaRepositoryService.getAllByQWriterPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("ANSSTATE"))
			{
				qna = classQnaRepositoryService.getAllByStatePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}

			return new ResClassQnaPage(qna);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassQnaPage("QnA 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/viewClassQna")
	public @ResponseBody ResClassQna viewClassQna(Member member, String seq)
	{
		System.out.println("api/viewClassQna");

		ClassQna qna = null;

		try
		{
			qna = classQnaRepositoryService.findBySeq(Integer.parseInt(seq));
			
			if(qna.isbSec() == false)
			{
				return new ResClassQna(qna, member);
			}
			else if(member != null && (qna.getqWriter().getUsername().equals(member.getUsername()) || member.getLevel().equals("LEVEL_TEACHER") || member.getLevel().equals("LEVEL_ADMIN")))
			{
				return new ResClassQna(qna, member);
			}
			else
			{
				return new ResClassQna("해당 게시물에 권한이 없습니다.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassQna("QnA 데이터를 읽어오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassQna")
	public @ResponseBody ResClassQnaPage deleteClassQna(Member member, String seq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassQna");
		
		try
		{
			ClassQna qna = classQnaRepositoryService.findBySeq(Integer.parseInt(seq));
			
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN") && !qna.getqWriter().getUsername().equals(member.getUsername())))
				return new ResClassQnaPage("해당 게시물 삭제 권한이 없습니다.");
			
			classQnaRepositoryService.removeClassQnaBySeq(Integer.parseInt(seq));
		
			return listClassQna(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassQnaPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/deleteListClassQna")
	public @ResponseBody ResClassQnaPage deleteListClassQna(Member member, String listSeq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteListClassQna");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassQnaPage("해당 게시글을 삭제할 권한이 없습니다.");
			
			classQnaRepositoryService.removeByListClassQnaSeq(listSeq.split(":"));
		
			return listClassQna(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassQnaPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}	
	
	@RequestMapping("/api/classHomework")
	public @ResponseBody ResClassHomeworkPage classHomework(Member member, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/classHomework");
		
		try
		{
			Page<ClassHomework> homework = null;
	
			if(option.equals("ALL"))
			{
				homework = classHomeworkRepositoryService.getAllByTitleContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("TITLE"))
			{
				homework = classHomeworkRepositoryService.getAllByTitlePageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			else if(option.equals("CONTENT"))
			{
				homework = classHomeworkRepositoryService.getAllByContentPageable(word, classSeq, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "seq")));
			}
			
			List<ClassReport> classReport = classReportRepositoryService.getAllByClassSeq(classSeq, member.getUsername(), new Sort(Direction.ASC, "seq"));
			
			return new ResClassHomeworkPage(homework,classReport);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassHomeworkPage("과제리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/viewClassHomework")
	public @ResponseBody ResClassHomework viewClassHomework(Member member, int homeworkSeq, String option, String word, String pageIdx)
	{
		System.out.println("/api/viewClassHomework");
		
		try
		{
			
			if(member == null)
				return new ResClassHomework("해당 게시글에 대한 권한이 없습니다.");
			
			ClassHomework homework = null;
	
			homework = classHomeworkRepositoryService.findBySeq(homeworkSeq);
			
			ClassReport classReport = classReportRepositoryService.getByHomeworkSeqUsername(homeworkSeq, member.getUsername(), new Sort(Direction.ASC, "seq"));
			
			return new ResClassHomework(homework,classReport); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassHomework("과제 상세보기를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassHomeworkList")
	public @ResponseBody ResClassHomeworkPage deleteClassHomeworkList(Member member, String listSeq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassHomeworkList");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassHomeworkPage("해당 게시글을 삭제할 권한이 없습니다.");
			
			classHomeworkRepositoryService.removeHomeworkByListSeq(listSeq.split(":"));
		
			return classHomework(member, classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassHomeworkPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassHomework")
	public @ResponseBody ResClassHomeworkPage deleteClassHomework(Member member, String seq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassNotice");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassHomeworkPage("해당 게시글의 삭제 권한이 없습니다.");
		
			classHomeworkRepositoryService.removeBySeq(Integer.parseInt(seq));
		
			return classHomework(member, classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassHomeworkPage("과제를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/downloadClassHomework")
	public void downloadClassHomework(Member member, String filePath, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadClassHomework");
 		
		filePath = MyFileUtil.getInstance(request).getRootPath() + filePath;

		int idx = filePath.lastIndexOf("/");
		MyFileUtil.getInstance(request).fileOut( request, response, filePath, filePath.substring(idx+1));
		
	}
	
	@RequestMapping("/api/listClassMemberReport")
	public @ResponseBody ResOfflineClassMemberPage listClassMemberReport(Member member, int classSeq, int homeworkSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/listClassMemberReport");

		Page<OfflineClassMember> memberPage = null;

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassMemberPage("해당 게시글리스트에 대한 권한이 없습니다.");

			if(option.equals("ALL"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberRealNameUserNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			else if(option.equals("NAME"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberRealNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}
			else if(option.equals("ID"))
			{
				memberPage = offlineClassMemberRepositoryService.getAllByClassMemberUserNameBDeletePageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.ASC, "realname")));
			}

			List<ClassReport> classReport = classReportRepositoryService.getAllByHomeworkSeq(homeworkSeq, new Sort(Direction.ASC, "seq"));
			
			return new ResOfflineClassMemberPage(memberPage, classReport);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	
	@RequestMapping("/api/submitReportResult")
	public @ResponseBody ResOfflineClassMemberPage submitReportResult(Member member, String seqList, String pointList, int classSeq, int homeworkSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/submitReportResult");

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassMemberPage("해당 게시글리스트에 대한 권한이 없습니다.");

			classReportRepositoryService.saveReportResult(seqList,pointList);
			
			return listClassMemberReport(member, classSeq, homeworkSeq, option, word, pageIdx);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassMemberPage("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/downloadClassReport")
	public void downloadClassReport(Member member, String filePath, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadClassReport");
 		
		filePath = MyFileUtil.getInstance(request).getRootPath() + filePath;

		int idx = filePath.lastIndexOf("/");
		MyFileUtil.getInstance(request).fileOut( request, response, filePath, filePath.substring(idx+1));
		
	}
	
	@RequestMapping("/api/downloadReportAll")
	public void downloadReportAll(Member member, int classSeq, int homeworkSeq, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("api/downloadClassReportAll");
		List<String> fileList = new ArrayList<String>();
		try{
			List<ClassReport> classReport = classReportRepositoryService.getAllByHomeworkSeq(homeworkSeq, new Sort(Direction.ASC, "seq"));
			
			for(int i=0;i<classReport.size();i++){
				if(classReport.get(i).getFiles() != null && classReport.get(i).getFiles().length() > 0)
				{
					String files[] = classReport.get(i).getFiles().split(";");
					
					for(String file : files)
					{
						fileList.add(file);
					}
				}
			}
			
			MyFileUtil.getInstance(request).zipFileOut( request, response, fileList);
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	@RequestMapping("/api/listClassReview")
	public @ResponseBody ResClassReviewPage listClassReview(int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/listClassReview");

		Page<ClassReview> review = null;

		try
		{
			if(option.equals("ALL"))
			{
				review = classReviewRepositoryService.getAllByContentWriterPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("WRITER"))
			{
				review = classReviewRepositoryService.getAllByWriterPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}
			else if(option.equals("CONTENT"))
			{
				review = classReviewRepositoryService.getAllByContentPageable(classSeq, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
			}			

			return new ResClassReviewPage(review);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassReviewPage("수강후기 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/submitClassReview")
	public @ResponseBody ResClassReviewPage submitClassReview(Member member, String seq, String review, int classSeq, int point, String option, String word, String pageIdx)
	{
		System.out.println("api/submitClassReview");
				
		try
		{			
			int reviewSeq = Integer.parseInt(seq);
			
			if(reviewSeq<=0){
				if(member == null || !member.getLevel().equals("LEVEL_MEMBER"))
					return new ResClassReviewPage("해당 게시물 등록 권한이 없습니다.");
			}else{
				ClassReview classReview = classReviewRepositoryService.findBySeq(reviewSeq);
				
				if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN") && !classReview.getWriter().getUsername().equals(member.getUsername())))
					return new ResClassReviewPage("해당 게시물 수정 권한이 없습니다.");					
			}
			
			classReviewRepositoryService.saveReview(reviewSeq, classSeq, point, review, member);
			
			return listClassReview(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassReviewPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassReview")
	public @ResponseBody ResClassReviewPage deleteClassReview(Member member, String seq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassReview");
		
		try
		{
			ClassReview review = classReviewRepositoryService.findBySeq(Integer.parseInt(seq));
			
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN") && !review.getWriter().getUsername().equals(member.getUsername())))
				return new ResClassReviewPage("해당 게시물 삭제 권한이 없습니다.");
			
			classReviewRepositoryService.removeClassReviewBySeq(Integer.parseInt(seq));
		
			return listClassReview(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassReviewPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
		
	@RequestMapping("/api/deleteListClassReview")
	public @ResponseBody ResClassReviewPage deleteListClassReview(Member member, String listSeq, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteListClassReview");
		
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassReviewPage("해당 게시글을 삭제할 권한이 없습니다.");
			
			classReviewRepositoryService.removeByListClassReviewSeq(listSeq.split(":"));
		
			return listClassReview(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassReviewPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/deleteClassReviewAll")
	public @ResponseBody ResClassReviewPage deleteClassReviewAll(Member member, int classSeq, String option, String word, String pageIdx)
	{
		System.out.println("api/deleteClassReviewAll");
		
		try
		{						
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResClassReviewPage("해당 게시물 삭제 권한이 없습니다.");
			
			List<ClassReview> tempClassReview = new ArrayList<ClassReview>();
			String listReviewSeq = "";
			
			tempClassReview = classReviewRepositoryService.getReviewListByClassSeq(classSeq,new Sort(Direction.DESC, "regDate"));
			
			for(ClassReview classReview : tempClassReview){
				listReviewSeq = listReviewSeq + classReview.getSeq()+":";
			}
			
			listReviewSeq = listReviewSeq.substring(0,listReviewSeq.lastIndexOf( ":"));
			
			classReviewRepositoryService.removeByListClassReviewSeq(listReviewSeq.split(":"));
		
			return listClassReview(classSeq, option, word, pageIdx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResClassReviewPage("데이터를 삭제하는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/classMemberClassList")
	public @ResponseBody ResOfflineClassPage classMemberClassList(Member member, String username, String state, String type, String word, String pageIdx)
	{
		System.out.println("api/classMemberClassList");

		Page<OfflineClass> offlineClassPage = null;
		List<OfflineClassMember> tempClassMember = new ArrayList<OfflineClassMember>();
		List<Integer> listClassSeq = new ArrayList<Integer>();

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ResOfflineClassPage("해당 게시글리스트에 대한 권한이 없습니다.");
			
			Member tempMember = memberRepositoryService.findByIdBDelete(username, false);
			
			if(tempMember==null){
				return null;
			}
			
			if(tempMember.getLevel().equals("LEVEL_TEACHER")||tempMember.getLevel().equals("LEVEL_ADMIN"))
			{				
				if(state.equals("ALL")) /*기간 - 전체*/
				{
					offlineClassPage = offlineClassRepositoryService.getAllClassByBDeletePageable(tempMember.getRealname(), false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				else if(state.equals("WAIT")) /*기간 - 대기(신청전)*/
				{
					offlineClassPage = offlineClassRepositoryService.getWaitClassByBDeletePageable(tempMember.getRealname(), false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}			
				else if(state.equals("APPLY")) /*기간 - 신청중*/
				{
					offlineClassPage = offlineClassRepositoryService.getApplyClassByBDeletePageable(tempMember.getRealname(), false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				else if(state.equals("START")) /*기간 - 강의중*/
				{
					offlineClassPage = offlineClassRepositoryService.getStartClassByBDeletePageable(tempMember.getRealname(), false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				else if(state.equals("END")) /*기간 - 종강*/
				{
					offlineClassPage = offlineClassRepositoryService.getEndClassByBDeletePageable(tempMember.getRealname(), false, type,  word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}			
				return new ResOfflineClassPage(offlineClassPage);
				
			}else if (tempMember.getLevel().equals("LEVEL_MEMBER")){
			
				tempClassMember = offlineClassMemberRepositoryService.getClassSeqByUserName(username, new Sort(Direction.DESC, "regDate"));
				
				if(tempClassMember.size()<=0)
					return null;
				
				for(OfflineClassMember classMember : tempClassMember){
					listClassSeq.add(classMember.getClassSeq());
				}
						
				if(state.equals("ALL")) /*기간 - 전체*/
				{
					offlineClassPage = offlineClassRepositoryService.getAllMyClassByBDeletePageable(false, listClassSeq, type, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}			
				else if(state.equals("APPLY")) /*기간 - 신청중*/
				{
					offlineClassPage = offlineClassRepositoryService.getApplyMyClassByBDeletePageable(false, listClassSeq, type, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				else if(state.equals("WAIT")) /*기간 - 대기(신청후)*/
				{
					offlineClassPage = offlineClassRepositoryService.getWaitMyClassByBDeletePageable(false, listClassSeq, type, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}			
				else if(state.equals("START")) /*기간 - 강의중*/
				{
					offlineClassPage = offlineClassRepositoryService.getStartMyClassByBDeletePageable(false, listClassSeq, type, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}
				else if(state.equals("END")) /*기간 - 종강*/
				{
					offlineClassPage = offlineClassRepositoryService.getEndMyClassByBDeletePageable(false, listClassSeq, type, word, PageRequest.of(Integer.parseInt(pageIdx), 10, new Sort(Direction.DESC, "regDate")));
				}			
				
				return new ResOfflineClassPage(offlineClassPage, tempClassMember);
			}
			
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResOfflineClassPage("강좌정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}
	
	@RequestMapping("/api/listClassAttendance")
	public @ResponseBody ViewClassAttendance listClassAttendance(Member member, int classSeq)
	{
		System.out.println("api/listClassAttendance");

		ViewClassAttendance classAttendance = new ViewClassAttendance();
		List<ClassAttendance> attendanceList = null;
		List<OfflineClassMember> memberList = null;		

		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return new ViewClassAttendance("해당 게시글리스트에 대한 권한이 없습니다.");
			
			attendanceList = classAttendanceRepositoryService.getAllByClassSeq(classSeq, new Sort(Direction.ASC, "username.realname"));			
			memberList = offlineClassMemberRepositoryService.getClassMemberListByClassSeq(classSeq, new Sort(Direction.ASC, "realname"));		

			classAttendance.setListClassAttendance(attendanceList);
			classAttendance.setListOfflineClassMember(memberList);
			
			return classAttendance;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ViewClassAttendance("회원정보 리스트를 가져오는 중 오류가 발생하였습니다.");
		}
	}

	@RequestMapping("/api/classMemberReviewSubmit")
	public @ResponseBody String classMemberReviewSubmit(Member member, int classSeq, String username, String content)
	{
		System.out.println("api/classMemberReviewSubmit");

		ClassAttendance saveTemp;
		try
		{
			if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN")))
				return "LEVEL error";

			Optional<ClassAttendance> temp = classAttendanceRepositoryService.getByClassSeqUsername(classSeq, username);
			
			if(temp.isPresent()){
				saveTemp = temp.get();
			}else{				
				saveTemp = new ClassAttendance();
				
				saveTemp.setSeq(-1);
				saveTemp.setClassSeq(classSeq);				
				
				Member tempMember = memberRepositoryService.findByIdBDelete(username,false);				
				saveTemp.setUsername(tempMember);
			}
			
			saveTemp.setContent(content);
			saveTemp.setWriter(member.getUsername());
						
			classAttendanceRepositoryService.saveAttendance(saveTemp);
			return "OK";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "학생관리내용을 저장하는중 오류가 발생하였습니다.";
		}
	}
	
	@RequestMapping("/api/classAttendanceSubmit")
	public @ResponseBody ViewClassAttendance classAttendanceSubmit(Member member, ClassAttendanceList checklist, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		System.out.println("api/classAttendanceSubmit");

		if(member == null || (!member.getLevel().equals("LEVEL_TEACHER") && !member.getLevel().equals("LEVEL_ADMIN"))){
			return new ViewClassAttendance("해당 게시물 작성 권한이 없습니다.");		
		}
		
		try
		{
			classAttendanceRepositoryService.savelist(checklist, member);
			return listClassAttendance(member,checklist.getCheckList().get(0).getClassSeq());
		}
		catch(Exception e)
		{
			e.printStackTrace();		
			return new ViewClassAttendance("출석부 저장중 오류가 발생하였습니다.");
		}		
	}
}
