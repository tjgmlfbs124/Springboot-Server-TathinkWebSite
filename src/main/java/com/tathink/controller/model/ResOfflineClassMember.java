package com.tathink.controller.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tathink.database.model.ClassReport;
import com.tathink.database.model.OfflineClassMember;

public class ResOfflineClassMember {
	private int seq;	
	private int classSeq;
	private String username;
	private String realname;
	private String mobile;
	private String protectorMobile;
    private LocalDate regDate;
	private String error;
	private int reportSeq;
	private String point;
	private LocalDateTime reportRegDate;
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	
	public ResOfflineClassMember(String strError)
	{
		setError(strError);
	}
	
	public ResOfflineClassMember(OfflineClassMember offlineClassMember)
	{
		error = null;
		seq = offlineClassMember.getSeq();
		classSeq = offlineClassMember.getClassSeq();
		username = offlineClassMember.getUsername();
		realname = offlineClassMember.getRealname();
		mobile = offlineClassMember.getMobile();
		protectorMobile = offlineClassMember.getProtectorMobile();
		regDate = offlineClassMember.getRegDate();
	}
	
	public ResOfflineClassMember(OfflineClassMember offlineClassMember, ClassReport classReport)
	{
		error = null;
		seq = offlineClassMember.getSeq();
		classSeq = offlineClassMember.getClassSeq();
		username = offlineClassMember.getUsername();
		realname = offlineClassMember.getRealname();
		mobile = offlineClassMember.getMobile();
		protectorMobile = offlineClassMember.getProtectorMobile();
		regDate = offlineClassMember.getRegDate();

		reportSeq = classReport.getSeq();
		point = classReport.getPoint();
		reportRegDate = classReport.getRegDate();

		if(classReport.getFiles() != null && classReport.getFiles().length() > 0)
		{
			String files[] = classReport.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}					
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getProtectorMobile() {
		return protectorMobile;
	}

	public void setProtectorMobile(String protectorMobile) {
		this.protectorMobile = protectorMobile;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public LocalDateTime getReportRegDate() {
		return reportRegDate;
	}

	public void setReportRegDate(LocalDateTime reportRegDate) {
		this.reportRegDate = reportRegDate;
	}

	public ArrayList<String> getListAttachedFiles() {
		return listAttachedFiles;
	}

	public void setListAttachedFiles(ArrayList<String> listAttachedFiles) {
		this.listAttachedFiles = listAttachedFiles;
	}

	public int getReportSeq() {
		return reportSeq;
	}

	public void setReportSeq(int reportSeq) {
		this.reportSeq = reportSeq;
	}

	
}
