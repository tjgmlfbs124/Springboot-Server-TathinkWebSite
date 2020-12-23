package com.tathink.controller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.tathink.database.model.ClassFile;

public class ResClassFile 
{
	private int seq;
	private int classSeq;
	private String title;
	private String content;
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	private LocalDateTime regDate;
	private String writer;
	private String error;
	
	public ResClassFile(ClassFile classFile)
	{
		error = null;
		seq = classFile.getSeq();
		classSeq = classFile.getClassSeq();
		title = classFile.getTitle();
		regDate = classFile.getRegDate();
		writer = classFile.getWriter().getUsername();
		content = classFile.getContent();
		
		if(classFile.getFiles() != null && classFile.getFiles().length() > 0)
		{
			String files[] = classFile.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
	}
		
	public ResClassFile(String strError)
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
