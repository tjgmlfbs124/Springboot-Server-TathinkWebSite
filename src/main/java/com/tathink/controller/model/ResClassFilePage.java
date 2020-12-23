package com.tathink.controller.model;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.tathink.database.model.ClassFile;

public class ResClassFilePage 
{
	private ArrayList<ResClassFile> fileList = new ArrayList<ResClassFile>();
	private int currPageIdx;
	private int startPageIdx;
	private int endPageIdx;
	private int pageCnt;
	private String error;
	
	public ResClassFilePage(Page<ClassFile> pageFile)
	{
		for(ClassFile file : pageFile.getContent())
		{
			ResClassFile resFile = new ResClassFile(file);
			resFile.setContent("");
			fileList.add(resFile);
		}
		
		currPageIdx = pageFile.getNumber() + 1;
		startPageIdx = ((pageFile.getNumber() / 10) * 10 ) + 1;
		endPageIdx = ((pageFile.getNumber() / 10) * 10 ) + 10; 
		pageCnt = pageFile.getTotalPages();
	}
	
	public ResClassFilePage(String strError)
	{
		error = strError;
	}

	public ArrayList<ResClassFile> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<ResClassFile> fileList) {
		this.fileList = fileList;
	}

	public int getCurrPageIdx() {
		return currPageIdx;
	}

	public void setCurrPageIdx(int currPageIdx) {
		this.currPageIdx = currPageIdx;
	}

	public int getStartPageIdx() {
		return startPageIdx;
	}

	public void setStartPageIdx(int startPageIdx) {
		this.startPageIdx = startPageIdx;
	}

	public int getEndPageIdx() {
		return endPageIdx;
	}

	public void setEndPageIdx(int endPageIdx) {
		this.endPageIdx = endPageIdx;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
