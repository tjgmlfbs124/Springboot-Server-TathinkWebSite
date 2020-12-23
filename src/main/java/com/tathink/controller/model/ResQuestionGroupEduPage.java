package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import com.tathink.database.model.QuestionGroupEdu;

public class ResQuestionGroupEduPage 
{
	private ArrayList<ResQuestionGroupEdu> listQuestionGroupEdu = new ArrayList<ResQuestionGroupEdu>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResQuestionGroupEduPage(Page<QuestionGroupEdu> pageQuestionGroupEdu)
	{
		for(QuestionGroupEdu questionGroupEdu : pageQuestionGroupEdu.getContent())
		{
			ResQuestionGroupEdu resQuestionGroupEdu = new ResQuestionGroupEdu(questionGroupEdu);
			listQuestionGroupEdu.add(resQuestionGroupEdu);
		}
		
		currPageIdx = pageQuestionGroupEdu.getNumber() + 1;
		startPageIdx = ((pageQuestionGroupEdu.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageQuestionGroupEdu.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageQuestionGroupEdu.getTotalPages();
		
		error = null;
	}
	
	public ResQuestionGroupEduPage(String strError)
	{
		error = strError;
	}

	public ArrayList<ResQuestionGroupEdu> getListQuestionGroupEdu() {
		return listQuestionGroupEdu;
	}

	public void setListQuestionGroupEdu(ArrayList<ResQuestionGroupEdu> listQuestionGroupEdu) {
		this.listQuestionGroupEdu = listQuestionGroupEdu;
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
