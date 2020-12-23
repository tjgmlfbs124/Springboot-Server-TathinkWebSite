package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.Course;

public class ResCourseInfoPage {
	private ArrayList<ResCourse> courseList = new ArrayList<ResCourse>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResCourseInfoPage(String strError)
	{
		error = strError;
	}
	
	public ResCourseInfoPage(Page<Course> pageCourse)
	{		
		for(Course course : pageCourse.getContent())
		{
			ResCourse resCourse = new ResCourse(course);
			courseList.add(resCourse);
		}
		
		currPageIdx = pageCourse.getNumber() + 1;
		startPageIdx = ((pageCourse.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageCourse.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageCourse.getTotalPages();
	}

	public ArrayList<ResCourse> getCourseList() {
		return courseList;
	}

	public void setMemberList(ArrayList<ResCourse> courseList) {
		this.courseList = courseList;
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
