package com.tathink.controller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tathink.database.model.ClassReport;

public class ResClassReport 
{
	private int seq;
	private int classSeq;
	private int homeworkSeq;
	private String point;
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	private LocalDateTime regDate;
	private String username;
	private String realname;
	private String error;
	
	public ResClassReport(ClassReport classReport)
	{
		error = null;
		seq = classReport.getSeq();
		classSeq = classReport.getClassSeq();
		regDate = classReport.getRegDate();
		username = classReport.getWriter().getUsername();
		realname = classReport.getWriter().getRealname();
		
		if(classReport.getFiles() != null && classReport.getFiles().length() > 0)
		{
			String files[] = classReport.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
	}
		
	public ResClassReport(String strError)
	{
		error = strError;
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
		
	public ArrayList<String> getListAttachedFiles() {
		return listAttachedFiles;
	}

	public void setListAttachedFiles(ArrayList<String> listAttachedFiles) {
		this.listAttachedFiles = listAttachedFiles;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getHomeworkSeq() {
		return homeworkSeq;
	}

	public void setHomeworkSeq(int homeworkSeq) {
		this.homeworkSeq = homeworkSeq;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}
