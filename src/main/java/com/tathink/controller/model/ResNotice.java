package com.tathink.controller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.tathink.database.model.Notice;

public class ResNotice 
{
	private int seq;
	private String title;
	private String content;
	private ArrayList<String> listAttachedFiles = new ArrayList<String>();
	private LocalDateTime regDate;
	private String writer;
	private String error;
	
	public ResNotice(Notice notice)
	{
		error = null;
		seq = notice.getSeq();
		title = notice.getTitle();
		regDate = notice.getRegDate();
		writer = notice.getWriter().getUsername();
		content = notice.getContent();
		
		if(notice.getFiles() != null && notice.getFiles().length() > 0)
		{
			String files[] = notice.getFiles().split(";");
			
			for(String file : files)
			{
				listAttachedFiles.add(file);
			}
		}
	}
		
	public ResNotice(String strError)
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
