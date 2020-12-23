package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.OfflineClass;

public class ResOfflineClass
{
	private int seq;
	private int offlineStudyInfoSeq;
	private String teacherUsername;
	private String teacherRealname;
	private int cost;
	private int no;
	private LocalDate startRegDate;
	private LocalDate endRegDate;
	private LocalDate startStudyDate;
	private LocalDate endStudyDate;
	private int capa;
	private String time;
	private String code;
	private String name;
	private int courseSeq;
	private String courseCode;
	private String courseName;
	private LocalDate regDate;
	private LocalDate memberRegDate;
	private String error;	

	public ResOfflineClass(String strError)
	{
		setError(strError);
	}
	
	public ResOfflineClass(OfflineClass offlineClass)
	{
		error = null;
		seq = offlineClass.getSeq();
		offlineStudyInfoSeq = offlineClass.getOfflineStudyInfoSeq();
		teacherUsername = offlineClass.getTeacherUsername();
		teacherRealname = offlineClass.getTeacherRealname();
		cost = offlineClass.getCost();
		no = offlineClass.getNo();
		startRegDate = offlineClass.getStartRegDate();
		endRegDate  = offlineClass.getEndRegDate();
		startStudyDate = offlineClass.getStartStudyDate();
		endStudyDate = offlineClass.getEndStudyDate();
		capa = offlineClass.getCapa();
		time = offlineClass.getTime();
		code = offlineClass.getCode();
		name = offlineClass.getName();
		courseSeq = offlineClass.getCourseSeq();
		courseCode = offlineClass.getCourseCode();
		courseName = offlineClass.getCourseName();
		regDate = offlineClass.getRegDate();
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getOfflineStudyInfoSeq() {
		return offlineStudyInfoSeq;
	}

	public void setOfflineStudyInfoSeq(int offlineStudyInfoSeq) {
		this.offlineStudyInfoSeq = offlineStudyInfoSeq;
	}

	public String getTeacherUsername() {
		return teacherUsername;
	}

	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}

	public String getTeacherRealname() {
		return teacherRealname;
	}

	public void setTeacherRealname(String teacherRealname) {
		this.teacherRealname = teacherRealname;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public LocalDate getStartRegDate() {
		return startRegDate;
	}

	public void setStartRegDate(LocalDate startRegDate) {
		this.startRegDate = startRegDate;
	}

	public LocalDate getEndRegDate() {
		return endRegDate;
	}

	public void setEndRegDate(LocalDate endRegDate) {
		this.endRegDate = endRegDate;
	}

	public LocalDate getStartStudyDate() {
		return startStudyDate;
	}

	public void setStartStudyDate(LocalDate startStudyDate) {
		this.startStudyDate = startStudyDate;
	}

	public LocalDate getEndStudyDate() {
		return endStudyDate;
	}

	public void setEndStudyDate(LocalDate endStudyDate) {
		this.endStudyDate = endStudyDate;
	}

	public int getCapa() {
		return capa;
	}

	public void setCapa(int capa) {
		this.capa = capa;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getCourseSeq() {
		return courseSeq;
	}

	public void setCourseSeq(int courseSeq) {
		this.courseSeq = courseSeq;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public LocalDate getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(LocalDate memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
}
