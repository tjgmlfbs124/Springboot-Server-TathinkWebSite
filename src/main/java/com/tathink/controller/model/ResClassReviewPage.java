package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassReview;

public class ResClassReviewPage 
{
	private ArrayList<ResClassReview> reviewList = new ArrayList<ResClassReview>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassReviewPage(Page<ClassReview> pageReview)
	{
		for(ClassReview review : pageReview.getContent())
		{
			ResClassReview resReview = new ResClassReview(review);
			reviewList.add(resReview);
		}
		
		currPageIdx = pageReview.getNumber() + 1;
		startPageIdx = ((pageReview.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageReview.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageReview.getTotalPages();
		
		error = null;
	}
	
	public ResClassReviewPage(String strError)
	{
		error = strError;
	}

	public List<ResClassReview> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<ResClassReview> reviewList) {
		this.reviewList = reviewList;
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
