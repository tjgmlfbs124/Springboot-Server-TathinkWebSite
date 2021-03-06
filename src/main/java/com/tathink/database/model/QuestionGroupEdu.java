package com.tathink.database.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionGroupEdu {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private String password;
	private LocalDateTime regDate;
	private String state;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
}
