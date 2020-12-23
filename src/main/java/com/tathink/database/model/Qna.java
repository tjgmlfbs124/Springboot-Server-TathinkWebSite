package com.tathink.database.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Qna {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
	private String qTitle;
	private String question;
	private String answer;
	private LocalDateTime qRegDate;
	private boolean bSec;
	private String password;
	private String state;
	@ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="qwriter")
	private Member qWriter;
	
	@ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="awriter")
	private Member aWriter;
	
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
	public LocalDateTime getqRegDate() {
		return qRegDate;
	}
	public void setqRegDate(LocalDateTime qRegDate) {
		this.qRegDate = qRegDate;
	}
	public boolean isbSec() {
		return bSec;
	}
	public void setbSec(boolean bSec) {
		this.bSec = bSec;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Member getqWriter() {
		return qWriter;
	}
	public void setqWriter(Member qWriter) {
		this.qWriter = qWriter;
	}
	public Member getaWriter() {
		return aWriter;
	}
	public void setaWriter(Member aWriter) {
		this.aWriter = aWriter;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
