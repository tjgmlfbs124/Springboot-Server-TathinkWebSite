package com.tathink.controller.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tathink.TathinkApplication;

public class ReqNoticeWrite {
	private String seq;
	private String title;
	private String editor;
	private String delFiles;
	MultipartHttpServletRequest mhsq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getDelFiles() {
		return delFiles;
	}
	public void setDelFiles(String delFiles) {
		this.delFiles = delFiles;
	}
	public MultipartHttpServletRequest getMhsq() {
		return mhsq;
	}
	public void setMhsq(MultipartHttpServletRequest mhsq) {
		this.mhsq = mhsq;
	}

	public String getFiles(String oriFileNames)
	{
		String[] listOriFileName;
		StringBuilder retFileNames = new StringBuilder();
		
		if(oriFileNames != null && oriFileNames.length() > 0)
		{
			listOriFileName = oriFileNames.split(";");
			
			for(String fileName : listOriFileName)
			{
				if(isDeletedFileName(fileName) == false)
				{
					retFileNames.append(fileName+";");
				}
			}
		}
		
		List<MultipartFile> listMf = mhsq.getFiles("attachFiles");   
		
		for(MultipartFile mf : listMf)
		{
			if( (mf.getSize() != 0) && (!mf.getOriginalFilename().equals("")) )
            {
				retFileNames.append(TathinkApplication.noticeFileRealPath + seq + "/" + mf.getOriginalFilename()+";");
            }
		}
		
		return retFileNames.toString();
	}
	
	public boolean isDeletedFileName(String fileName)
	{
		String[] listDelFileName = null;
		
		if(delFiles != null && delFiles.length() > 0)
		{
			listDelFileName = delFiles.split(";");
			
			for(String delFileName : listDelFileName)
			{
				if(fileName.equals(delFileName))
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
