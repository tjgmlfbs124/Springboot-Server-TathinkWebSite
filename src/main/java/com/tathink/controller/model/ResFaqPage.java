package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.Faq;

public class ResFaqPage {
	private List<Faq> faqList = null;
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResFaqPage(String strError)
	{
		error = strError;
		faqList=new ArrayList<Faq>();
	}
	
	public ResFaqPage(Page<Faq> pageFaq)
	{
		faqList = pageFaq.getContent();
		currPageIdx = pageFaq.getNumber() + 1;
		startPageIdx = ((pageFaq.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageFaq.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageFaq.getTotalPages();
	}

	public List<Faq> getFaqList() {
		return faqList;
	}

	public void setFaqList(List<Faq> faqList) {
		this.faqList = faqList;
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
