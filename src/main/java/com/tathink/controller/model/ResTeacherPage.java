package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.Member;

public class ResTeacherPage {
	private ArrayList<ResTeacher> memberList = new ArrayList<ResTeacher>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResTeacherPage(String strError)
	{
		error = strError;
	}
	
	public ResTeacherPage(Page<Member> pageMember)
	{		
		for(Member member : pageMember.getContent())
		{
			ResTeacher resMember = new ResTeacher(member);
			memberList.add(resMember);
		}
		
		currPageIdx = pageMember.getNumber() + 1;
		startPageIdx = ((pageMember.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageMember.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageMember.getTotalPages();
	}

	public ArrayList<ResTeacher> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<ResTeacher> memberList) {
		this.memberList = memberList;
	}

	public int getCurrPageIdx() {
		return currPageIdx;
	}

	public void setCurrPageIdx(int currPageIdx) {
		this.currPageIdx = currPageIdx;
	}

	public int getStartPageIdx() {
		return startPageIdx;
	}

	public void setStartPageIdx(int startPageIdx) {
		this.startPageIdx = startPageIdx;
	}

	public int getEndPageIdx() {
		return endPageIdx;
	}

	public void setEndPageIdx(int endPageIdx) {
		this.endPageIdx = endPageIdx;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
