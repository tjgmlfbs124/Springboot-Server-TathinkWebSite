package com.tathink.controller.model;

import com.tathink.database.model.Member;

public class ResTeacher 
{
    private String realname;
    private String imgPath;
    private String jobPosition;
    private String teacherInfo;
	private String error;
	
	public ResTeacher(String strError)
	{
		error = strError;
	}
	
	public ResTeacher(Member member)
	{
		error = null;
	    realname = member.getRealname();
	    imgPath = member.getImgPath();
	    jobPosition = member.getJobPosition();
	    teacherInfo = member.getTeacherInfo();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
