package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.OfflineClassMember;

public class ReqOfflineClassMember {
	private int classSeq;
	private String username;
	private String realname;
	private String mobile;
	private String protectorMobile;
	
	public OfflineClassMember convertToOfflineClassMember() {
		
		OfflineClassMember offlineClassMember = new OfflineClassMember();
		
		offlineClassMember.setClassSeq(getClassSeq());
		offlineClassMember.setUsername(getUsername());
		offlineClassMember.setRealname(getRealname());
		offlineClassMember.setMobile(getMobile());
		offlineClassMember.setProtectorMobile(getProtectorMobile());
		offlineClassMember.setRegDate(LocalDate.now());
		
		return offlineClassMember;
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

	public String getProtectorMobile() {
		return protectorMobile;
	}

	public void setProtectorMobile(String protectorMobile) {
		this.protectorMobile = protectorMobile;
	}
}
