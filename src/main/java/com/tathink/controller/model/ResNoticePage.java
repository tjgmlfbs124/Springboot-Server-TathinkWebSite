package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import com.tathink.database.model.Notice;

public class ResNoticePage 
{
	private ArrayList<ResNotice> noticeList = new ArrayList<ResNotice>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResNoticePage(Page<Notice> pageNotice)
	{
		for(Notice notice : pageNotice.getContent())
		{
			ResNotice resNotice = new ResNotice(notice);
			resNotice.setContent("");
			noticeList.add(resNotice);
		}
		
		currPageIdx = pageNotice.getNumber() + 1;
		startPageIdx = ((pageNotice.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageNotice.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageNotice.getTotalPages();
	}
	
	public ResNoticePage(String strError)
	{
		error = strError;
	}

	public ArrayList<ResNotice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(ArrayList<ResNotice> noticeList) {
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
