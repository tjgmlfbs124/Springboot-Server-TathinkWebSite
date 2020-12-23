package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassNotice;

public class ResClassNoticePage 
{
	private ArrayList<ResClassNotice> noticeList = new ArrayList<ResClassNotice>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassNoticePage(Page<ClassNotice> pageNotice)
	{
		for(ClassNotice notice : pageNotice.getContent())
		{
			ResClassNotice resNotice = new ResClassNotice(notice);
			resNotice.setContent("");
			noticeList.add(resNotice);
		}
		
		currPageIdx = pageNotice.getNumber() + 1;
		startPageIdx = ((pageNotice.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageNotice.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageNotice.getTotalPages();
	}
	
	public ResClassNoticePage(String strError)
	{
		error = strError;
	}

	public ArrayList<ResClassNotice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(ArrayList<ResClassNotice> noticeList) {
		this.noticeList = noticeList;
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
