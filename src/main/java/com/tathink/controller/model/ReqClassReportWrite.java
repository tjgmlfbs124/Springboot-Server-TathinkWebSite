package com.tathink.controller.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tathink.TathinkApplication;

public class ReqClassReportWrite {
	private String seq;
	private String classSeq;
	private String homeworkSeq;
	private String point;
	private String delFiles;
	MultipartHttpServletRequest mhsq;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
	}
	public String getHomeworkSeq() {
		return homeworkSeq;
	}
	public void setHomeworkSeq(String homeworkSeq) {
		this.homeworkSeq = homeworkSeq;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
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
				retFileNames.append(TathinkApplication.classReportFileRealPath + seq + "/" + mf.getOriginalFilename()+";");
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
