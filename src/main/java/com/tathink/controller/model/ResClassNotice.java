package com.tathink.controller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tathink.database.model.ClassNotice;

public class ResClassNotice 
{
	private int seq;
	private int classSeq;
	private String title;
	private String content;
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	private LocalDateTime regDate;
	private String writer;
	private String error;
	
	public ResClassNotice(ClassNotice classNotice)
	{
		error = null;
		seq = classNotice.getSeq();
		classSeq = classNotice.getClassSeq();
		title = classNotice.getTitle();
		regDate = classNotice.getRegDate();
		writer = classNotice.getWriter().getUsername();
		content = classNotice.getContent();
		
		if(classNotice.getFiles() != null && classNotice.getFiles().length() > 0)
		{
			String files[] = classNotice.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
	}
		
	public ResClassNotice(String strError)
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
}
