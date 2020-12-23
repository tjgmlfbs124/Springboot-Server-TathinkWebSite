package com.tathink.database.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class OfflineClassMember {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;	
	private int classSeq;
	private String username;
	private String realname;
	private String mobile;
	private String protectorMobile;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
	
	public int getSeq() 
	{
		return seq;
	}
	
	public void setSeq(int seq) 
	{
		this.seq = seq;
	}
    
	public int getClassSeq() 
	{
		return classSeq;
	}
	
	public void setClassSeq(int classSeq) 
	{
		this.classSeq = classSeq;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public LocalDate getRegDate() {
		return regDate;
	}
	
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getProtectorMobile() {
		return protectorMobile;
	}

	public void setProtectorMobile(String protectorMobile) {
		this.protectorMobile = protectorMobile;
	}
}
