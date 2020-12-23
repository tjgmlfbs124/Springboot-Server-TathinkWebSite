package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassQna;

public class ResClassQnaPage 
{
	private ArrayList<ResClassQna> qnaList = new ArrayList<ResClassQna>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassQnaPage(Page<ClassQna> pageQna)
	{
		for(ClassQna qna : pageQna.getContent())
		{
			ResClassQna resQna = new ResClassQna(qna);
			resQna.setAnswer(null);
			qnaList.add(resQna);
		}
		
		currPageIdx = pageQna.getNumber() + 1;
		startPageIdx = ((pageQna.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageQna.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageQna.getTotalPages();
		
		error = null;
	}
	
	public ResClassQnaPage(String strError)
	{
		error = strError;
	}

	public List<ResClassQna> getQnaList() {
		return qnaList;
	}

	public void setQnaList(ArrayList<ResClassQna> qnaList) {
		this.qnaList = qnaList;
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
