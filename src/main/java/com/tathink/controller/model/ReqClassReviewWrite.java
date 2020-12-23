package com.tathink.controller.model;

public class ReqClassReviewWrite {
	private int seq;
	private int classSeq;
	private String review;
	private int ponit;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(int classSeq) {
		this.classSeq = classSeq;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getPonit() {
		return ponit;
	}
	public void setPonit(int ponit) {
		this.ponit = ponit;
	}		
}
