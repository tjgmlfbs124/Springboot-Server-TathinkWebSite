package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassReport;

public class ResClassReportPage 
{
	private ArrayList<ResClassReport> reportList = new ArrayList<ResClassReport>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassReportPage(Page<ClassReport> pageReport)
	{
		for(ClassReport report : pageReport.getContent())
		{
			ResClassReport resReport = new ResClassReport(report);
			reportList.add(resReport);
		}
		
		currPageIdx = pageReport.getNumber() + 1;
		startPageIdx = ((pageReport.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageReport.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageReport.getTotalPages();
	}
	
	public ResClassReportPage(String strError)
	{
		error = strError;
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

	public ArrayList<ResClassReport> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<ResClassReport> reportList) {
		this.reportList = reportList;
	}
}
