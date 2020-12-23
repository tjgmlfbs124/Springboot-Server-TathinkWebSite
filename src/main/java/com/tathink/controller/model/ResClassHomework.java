package com.tathink.controller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.ClassReport;

public class ResClassHomework 
{
	private int seq;
	private int classSeq;
	private String title;
	private String content;
	private String applyDate;
	private String processDate;	
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	private LocalDateTime regDate;
	private String writer;
	private String error;
	private String point;
	private boolean submit;
	private ClassReport classReport;
	private ArrayList<String> listReportAttachedFiles = new ArrayList<String>();
	
	public ResClassHomework(ClassHomework classHomework)
	{
		error = null;
		seq = classHomework.getSeq();
		classSeq = classHomework.getClassSeq();
		title = classHomework.getTitle();
		content = classHomework.getContent();
		applyDate = classHomework.getApplyDate();
		processDate = classHomework.getProcessDate();
		regDate = classHomework.getRegDate();
		writer = classHomework.getWriter().getUsername();
		point = "";
		setSubmit(false);
		
		if(classHomework.getFiles() != null && classHomework.getFiles().length() > 0)
		{
			String files[] = classHomework.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
	}
	
	public ResClassHomework(ClassHomework classHomework, ClassReport classReport)
	{
		error = null;
		seq = classHomework.getSeq();
		classSeq = classHomework.getClassSeq();
		title = classHomework.getTitle();
		content = classHomework.getContent();
		applyDate = classHomework.getApplyDate();
		processDate = classHomework.getProcessDate();
		regDate = classHomework.getRegDate();
		writer = classHomework.getWriter().getUsername();
		point = "";
		setSubmit(false);
		setClassReport(classReport); 
		
		if(classHomework.getFiles() != null && classHomework.getFiles().length() > 0)
		{
			String files[] = classHomework.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
		
		if(classReport != null){
			if(classReport.getFiles() != null && classReport.getFiles().length() > 0)
			{
				String files[] = classReport.getFiles().split(";");
				
				for(String file : files)
				{
					listReportAttachedFiles.add(file);
				}
			}			
		}
	}
		
	public ResClassHomework(String strError)
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public boolean isSubmit() {
		return submit;
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}

	public ClassReport getClassReport() {
		return classReport;
	}

	public void setClassReport(ClassReport classReport) {
		this.classReport = classReport;
	}
	public ArrayList<String> getListReportAttachedFiles() {
		return listReportAttachedFiles;
	}

	public void setListReportAttachedFiles(ArrayList<String> listReportAttachedFiles) {
		this.listReportAttachedFiles = listReportAttachedFiles;
	}
}
