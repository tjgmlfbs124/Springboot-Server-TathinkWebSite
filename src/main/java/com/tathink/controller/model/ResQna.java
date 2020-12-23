package com.tathink.controller.model;

import java.time.LocalDateTime;

import com.tathink.database.model.Member;
import com.tathink.database.model.Qna;

public class ResQna 
{
	private int seq;
	private String error;
	private String qTitle;
	private LocalDateTime qRegDate;
  	private String qWriter;
  	private String question;
  	private String answer;
  	private String state;
  	private boolean bSec;
	private boolean bMine = false;
	
	public ResQna(Qna qna, Member member)
	{
		error = null;
		seq = qna.getSeq();
		qTitle = qna.getqTitle();
		qRegDate = qna.getqRegDate();
		qWriter = qna.getqWriter().getUsername();
		question = qna.getQuestion();
		answer = qna.getAnswer();
		state =  qna.getState();
		bSec =  qna.isbSec();
		if(member != null)
		{
			bMine = (member.getUsername().equals(qna.getqWriter().getUsername()));
		}
	}
		
	public ResQna(Qna qna)
	{
		error = null;
		seq = qna.getSeq();
		qTitle = qna.getqTitle();
		qRegDate = qna.getqRegDate();
		qWriter = qna.getqWriter().getUsername();
		question = qna.getQuestion();
		answer = qna.getAnswer();
		state =  qna.getState();
		bSec =  qna.isbSec();
	}
		
	public ResQna(String strError)
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

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public LocalDateTime getqRegDate() {
		return qRegDate;
	}

	public void setqRegDate(LocalDateTime qRegDate) {
		this.qRegDate = qRegDate;
	}

	public String getqWriter() {
		return qWriter;
	}

	public void setqWriter(String qWriter) {
		this.qWriter = qWriter;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isbSec() {
		return bSec;
	}

	public void setbSec(boolean bSec) {
		this.bSec = bSec;
	}

	public boolean isbMine() {
		return bMine;
	}

	public void setbMine(boolean bMine) {
		this.bMine = bMine;
	}
	
}
