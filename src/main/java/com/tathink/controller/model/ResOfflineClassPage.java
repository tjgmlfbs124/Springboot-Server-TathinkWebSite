package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineClassMember;

public class ResOfflineClassPage {
	private ArrayList<ResOfflineClass> offlineClassList = new ArrayList<ResOfflineClass>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResOfflineClassPage(String strError)
	{
		error = strError;
	}
	
	public ResOfflineClassPage(Page<OfflineClass> pageOfflineClass)
	{		
		for(OfflineClass offlineClass : pageOfflineClass.getContent())
		{
			ResOfflineClass resOfflineClass = new ResOfflineClass(offlineClass);
			offlineClassList.add(resOfflineClass);
		}
		
		currPageIdx = pageOfflineClass.getNumber() + 1;
		startPageIdx = ((pageOfflineClass.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageOfflineClass.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageOfflineClass.getTotalPages();
	}
	
	public ResOfflineClassPage(Page<OfflineClass> pageOfflineClass, List<OfflineClassMember> classMember)
	{				
		for(int i=0;i<classMember.size();i++){
			for(OfflineClass offlineClass : pageOfflineClass.getContent())
			{
				ResOfflineClass resOfflineClass = new ResOfflineClass(offlineClass);
							
				if(resOfflineClass.getSeq()==classMember.get(i).getClassSeq()){
					resOfflineClass.setMemberRegDate(classMember.get(i).getRegDate());
					offlineClassList.add(resOfflineClass);
					break;
				}
			}					
		}
		
		currPageIdx = pageOfflineClass.getNumber() + 1;
		startPageIdx = ((pageOfflineClass.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageOfflineClass.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageOfflineClass.getTotalPages();
	}

	public ArrayList<ResOfflineClass> getOfflineClassList() {
		return offlineClassList;
	}

	public void setOfflineClassList(ArrayList<ResOfflineClass> offlineClassList) {
		this.offlineClassList = offlineClassList;
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
