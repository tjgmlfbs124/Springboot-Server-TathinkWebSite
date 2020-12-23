package com.tathink.controller;

import java.io.File;

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
import com.tathink.controller.model.ReqMember;
import com.tathink.database.model.Member;
import com.tathink.database.repository.MemberRepositoryService;
import com.tathink.util.MyFileUtil;


@Controller
public class JoinController {
	@Autowired
	TempleteSetter templeteSetter;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	@RequestMapping("/join/login")
	public String login(Member member, String error, Model model)
	{	
		System.out.println("login");

		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinLogin");
		model.addAttribute("error", error);
		
		return "join/joinLogin";
	}
	
	@RequestMapping("/join/reg")
	public String reg(HttpSession session, Member member, Model model)
	{	
		System.out.println("reg");
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinReg");
		
		session.setAttribute("EmailAuthForRegMember", "");
		session.setAttribute("EmailAuthConfirmForRegMember", "");
		
		return "join/joinReg";
	}
	
	@RequestMapping("/join/regSubmit")
	public String regSubmit(HttpSession session, Member member, MultipartHttpServletRequest mhsq, ReqMember memberInfo, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{	
		System.out.println("regSubmit");
		
		String rootPath = MyFileUtil.getInstance(request).getRootPath();//request.getSession().getServletContext().getRealPath("/");
		
		try
		{		
			String authConfirm = (String)session.getAttribute("EmailAuthConfirmForRegMember");
			session.setAttribute("EmailAuthConfirmForRegMember", "");
			
			if(authConfirm == null || authConfirm.equals("false"))
			{
				redirectAttributes.addAttribute("error","이메일 인증이 되지 않았습니다.");
				return "redirect:join/errorPage";
			}
			
			if(memberInfo.getUsername() == null || memberInfo.getUsername().length() == 0)
			{
				redirectAttributes.addAttribute("error","회원 가입중 오류가 발생하였습니다.(1)");
				return "redirect:join/errorPage";
			}
			
			File dir = new File(rootPath + TathinkApplication.memberImageRealPath + memberInfo.getUsername());
			
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
	        
	        
	        memberRepositoryService.AddMember(memberInfo.ConvertToMember());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			redirectAttributes.addAttribute("error","회원 가입중 오류가 발생하였습니다.(2)");
			return "redirect:join/errorPage";
		}
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinReg");
		
		return "join/joinRegResult";
	}
	
	@RequestMapping("/join/findIdPwd")
	public String findIdPwd(HttpSession session, Member member, Model model)
	{	
		System.out.println("findIdPwd");
		
		session.setAttribute("EmailAuthForFindPwd", "");
		
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinFindIdPwd");
		
		return "join/joinFindIdPwd";
	}
	
	@RequestMapping("/join/findIdResult")
	public String findIdResult(Member member, String name, String email01, String email02, Model model, RedirectAttributes redirectAttributes)
	{	
		System.out.println("findIdResult");
		
		try
		{
			String resultString = "해당하는 아이디가 확인되지 않습니다.";
			StringBuilder stringBuilder = new StringBuilder();
			
			String email = email01 + "@" + email02;
			List<Member> listMember = null;
			
			listMember = memberRepositoryService.findByNameEmailBDelete(name, email, false, new Sort(Direction.DESC, "regDate"));
			
			if(listMember.size() > 0)
			{
				for(Member memberData : listMember)
				{
					stringBuilder.append(memberData.getRealname() + "님의 아이디는 " + memberData.getUsername() + "입니다.\n");
				}
				
				resultString = stringBuilder.toString();
			}
			
			templeteSetter.setHeader(member, model);
			model.addAttribute("pageName", "joinFindIdPwd");
			model.addAttribute("resultString", resultString);
		}
		catch(Exception e)
		{
			redirectAttributes.addAttribute("error","처리하는 도중 오류가 발생하였습니다.");
			e.printStackTrace();
			return "redirect:/join/errorPage";
		}
		return "join/joinFindIdResult";
	}
	
	@RequestMapping("/join/findPwdResult")
	public String findPwdResult(HttpSession session, Member member, String id ,String name, String email01, String email02, String authNum, Model model, RedirectAttributes redirectAttributes)
	{	
		try
		{
			String resultString = "해당하는 아이디가 확인되지 않습니다.";
			String sessionAuthNum = null;
			String email = email01+"@"+email02;
			Member memberData = null;
			
			System.out.println("findPwdResult");
			
			memberData = memberRepositoryService.findByIdBDelete(id, false);
			sessionAuthNum = (String)session.getAttribute("EmailAuthForFindPwd");
			session.setAttribute("EmailAuthForFindPwd", "");
			
			if(memberData == null)
			{
				resultString = "존재하지 않는 사용자 입니다.";
			}
			else if(memberData.getRealname().equals(name) == false || memberData.getEmail().equals(email) == false)
			{
				resultString = "존재하지 않는 사용자 입니다.";
			}
			else if(sessionAuthNum == null || sessionAuthNum.length() < 1 || sessionAuthNum.equals(authNum) == false)
			{
				resultString = "인증번호가 잘못 되었습니다..";
			}
			else
			{
				resultString = name+"님의 비밀번호는 "+ memberData.getPassword() +" 입니다.";
			}
			
			templeteSetter.setHeader(member, model);
			model.addAttribute("pageName", "joinFindIdPwd");
			model.addAttribute("resultString", resultString);
		}
		catch(Exception e)
		{
			redirectAttributes.addAttribute("error","처리하는 도중 오류가 발생하였습니다.");
			e.printStackTrace();
			return "redirect:/join/errorPage";
		}
		return "join/joinFindPwdResult";
	}
	
	@RequestMapping("/join/term")
	public String term(Member member, Model model)
	{
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinTerm");
		return "join/joinTerm";
	}
	
	@RequestMapping("/join/role")
	public String role(Member member, Model model)
	{
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinRole");
		return "join/joinRole";
	}
	
	@RequestMapping("/join/emailRole")
	public String emailRole(Member member, Model model)
	{
		templeteSetter.setHeader(member, model);
		model.addAttribute("pageName", "joinEmailRole");
		return "join/joinEmailRole";
	}

}
