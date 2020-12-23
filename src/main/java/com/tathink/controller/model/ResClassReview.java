package com.tathink.controller.model;

import java.time.LocalDateTime;

import com.tathink.database.model.ClassReview;
import com.tathink.database.model.Member;

public class ResClassReview 
{
	private int seq;
	private int classSeq;
	private String review;
  	private String writer;
  	private String writerName;
  	private int point;
	private LocalDateTime regDate;
  	private String error;
	private boolean bMine = false;
	
	public ResClassReview(ClassReview review, Member member)
	{
		this.error = null;
		this.seq = review.getSeq();
		this.classSeq = review.getClassSeq();
		this.review = review.getReview();
		this.writer = review.getWriter().getUsername();
		this.writerName = review.getWriter().getRealname();
		this.point = review.getPoint();
		this.regDate = review.getRegDate();	

		if(member != null)
		{
			setbMine((member.getUsername().equals(review.getWriter().getUsername())));
		}
	}
		
	public ResClassReview(ClassReview review)
	{
		this.error = null;
		this.seq = review.getSeq();
		this.classSeq = review.getClassSeq();
		this.review = review.getReview();
		this.writer = review.getWriter().getUsername();
		this.writerName = review.getWriter().getRealname();
		this.point = review.getPoint();
		this.regDate = review.getRegDate();	
	}
		
	public ResClassReview(String strError)
	{
		error = strError;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public boolean isbMine() {
		return bMine;
	}

	public void setbMine(boolean bMine) {
		this.bMine = bMine;
	}
	
}
