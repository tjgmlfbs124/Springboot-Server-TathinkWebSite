package com.tathink.controller.model;

import java.time.LocalDateTime;

import com.tathink.database.model.QuestionGroupEdu;

public class ResQuestionGroupEdu 
{
	private int seq;
	private String title;
	private String companyName;
	private String charder;
	private String mobile01;
	private String mobile02;
	private String mobile03;
	private String email01;
	private String email02;
	private String content;
	private LocalDateTime regDate;
	private String state;
	private String error;
	
	public ResQuestionGroupEdu(QuestionGroupEdu questionGroupEdu)
	{
		error = null;
		seq = questionGroupEdu.getSeq();
		title = questionGroupEdu.getTitle();
		companyName = questionGroupEdu.getCompanyName();
		charder = questionGroupEdu.getCharder();
		mobile01 = questionGroupEdu.getMobile01();
		mobile02 = questionGroupEdu.getMobile02();
		mobile03 = questionGroupEdu.getMobile03();
		email01 = questionGroupEdu.getEmail01();
		email02 = questionGroupEdu.getEmail02();
		content = questionGroupEdu.getContent();
		regDate = questionGroupEdu.getRegDate();
		state = questionGroupEdu.getState();
	}
		
	public ResQuestionGroupEdu(String strError)
	{
		error = strError;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCharder() {
		return charder;
	}

	public void setCharder(String charder) {
		this.charder = charder;
	}

	public String getMobile01() {
		return mobile01;
	}

	public void setMobile01(String mobile01) {
		this.mobile01 = mobile01;
	}

	public String getMobile02() {
		return mobile02;
	}

	public void setMobile02(String mobile02) {
		this.mobile02 = mobile02;
	}

	public String getMobile03() {
		return mobile03;
	}

	public void setMobile03(String mobile03) {
		this.mobile03 = mobile03;
	}

	public String getEmail01() {
		return email01;
	}

	public void setEmail01(String email01) {
		this.email01 = email01;
	}

	public String getEmail02() {
		return email02;
	}

	public void setEmail02(String email02) {
		this.email02 = email02;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
