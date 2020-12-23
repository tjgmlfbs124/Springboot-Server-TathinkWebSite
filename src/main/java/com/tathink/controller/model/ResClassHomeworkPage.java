package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.ClassReport;

public class ResClassHomeworkPage 
{
	private ArrayList<ResClassHomework> homeworkList = new ArrayList<ResClassHomework>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassHomeworkPage(Page<ClassHomework> pageHomework)
	{
		for(ClassHomework homework : pageHomework.getContent())
		{
			ResClassHomework resHomework = new ResClassHomework(homework);
			homeworkList.add(resHomework);
		}
		
		currPageIdx = pageHomework.getNumber() + 1;
		startPageIdx = ((pageHomework.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageHomework.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageHomework.getTotalPages();
	}
	
	public ResClassHomeworkPage(Page<ClassHomework> pageHomework, List<ClassReport> report)
	{
		for(ClassHomework homework : pageHomework.getContent())
		{
			ResClassHomework resHomework = new ResClassHomework(homework);
			resHomework.setSubmit(false);
			int seq = resHomework.getSeq();
			for(int i=0;i<report.size();i++ ){
				int hSeq=report.get(i).getHomeworkSeq();
				if(seq==hSeq){
					resHomework.setSubmit(true);
					if(report.get(i).getPoint()!=null && report.get(i).getPoint()!=""){
						resHomework.setPoint(report.get(i).getPoint());
						break;
					}					
				}
			}

			homeworkList.add(resHomework);
		}
		
		currPageIdx = pageHomework.getNumber() + 1;
		startPageIdx = ((pageHomework.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageHomework.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageHomework.getTotalPages();
	}
	
	public ResClassHomeworkPage(String strError)
	{
		error = strError;
	}

	public ArrayList<ResClassHomework> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(ArrayList<ResClassHomework> homeworkList) {
		this.homeworkList = homeworkList;
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
