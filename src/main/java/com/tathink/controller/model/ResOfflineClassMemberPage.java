package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassReport;
import com.tathink.database.model.OfflineClassMember;

public class ResOfflineClassMemberPage {
	private ArrayList<ResOfflineClassMember> offlineClassMemberList = new ArrayList<ResOfflineClassMember>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResOfflineClassMemberPage(String strError)
	{
		error = strError;
	}
	
	public ResOfflineClassMemberPage(Page<OfflineClassMember> pageOfflineClassMember)
	{		
		for(OfflineClassMember offlineClassMember : pageOfflineClassMember.getContent())
		{
			ResOfflineClassMember resOfflineClassMember = new ResOfflineClassMember(offlineClassMember);
			offlineClassMemberList.add(resOfflineClassMember);
		}
		
		currPageIdx = pageOfflineClassMember.getNumber() + 1;
		startPageIdx = ((pageOfflineClassMember.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageOfflineClassMember.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageOfflineClassMember.getTotalPages();
	}
	
	public ResOfflineClassMemberPage(Page<OfflineClassMember> pageOfflineClassMember, List<ClassReport> classReport)
	{		
		for(OfflineClassMember offlineClassMember : pageOfflineClassMember.getContent())
		{
			ResOfflineClassMember resOfflineClassMember = new ResOfflineClassMember(offlineClassMember);

			for(int i=0;i<classReport.size();i++){
				if(resOfflineClassMember.getUsername().equals(classReport.get(i).getWriter().getUsername()))
				{
					resOfflineClassMember = new ResOfflineClassMember(offlineClassMember,classReport.get(i));
					break;
				}
			}
			
			offlineClassMemberList.add(resOfflineClassMember);
		}
		
		currPageIdx = pageOfflineClassMember.getNumber() + 1;
		startPageIdx = ((pageOfflineClassMember.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageOfflineClassMember.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageOfflineClassMember.getTotalPages();
	}

	public ArrayList<ResOfflineClassMember> getOfflineClassMemberList() {
		return offlineClassMemberList;
	}

	public void setOfflineClassMemberList(ArrayList<ResOfflineClassMember> offlineClassMemberList) {
		this.offlineClassMemberList = offlineClassMemberList;
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
