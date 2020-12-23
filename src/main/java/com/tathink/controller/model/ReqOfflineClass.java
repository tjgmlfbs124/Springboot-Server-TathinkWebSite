package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineStudyInfo;

public class ReqOfflineClass

{
	private int seq = -1;
	private int offlineStudyInfoSeq;
	private String teacherUsername;
	private String teacherRealname;
	private int cost;
	private int no;
	private String startRegDate;
	private String endRegDate;
	private String startStudyDate;
	private String endStudyDate;
	private int capa;
	private String time;
	private String code;
	private String name;
	private int courseSeq;
	private String courseCode;
	private String courseName;
		
	public OfflineClass convertToOfflineClass() {
		
		OfflineClass offlineClass = new OfflineClass();
		
		offlineClass.setSeq(getSeq());
		offlineClass.setOfflineStudyInfoSeq(getOfflineStudyInfoSeq());
		offlineClass.setTeacherUsername(getTeacherUsername());
		offlineClass.setTeacherRealname(getTeacherRealname());
		offlineClass.setCost(getCost());
		offlineClass.setNo(getNo());
		offlineClass.setStartRegDate(LocalDate.parse(getStartRegDate()));
		offlineClass.setEndRegDate(LocalDate.parse(getEndRegDate()));
		offlineClass.setStartStudyDate(LocalDate.parse(getStartStudyDate()));
		offlineClass.setEndStudyDate(LocalDate.parse(getEndStudyDate()));
		offlineClass.setCapa(getCapa());
		offlineClass.setTime(getTime());
		offlineClass.setCode(getCode());
		offlineClass.setName(getName());
		offlineClass.setCourseSeq(getCourseSeq());
		offlineClass.setCourseCode(getCourseCode());
		offlineClass.setCourseName(getCourseName());
		offlineClass.setRegDate(LocalDate.now());
		offlineClass.setbDelete(false);
		
		return offlineClass;
	}
	
	public OfflineStudyInfo convertToStudyInfo() {
		
		OfflineStudyInfo offlineStudy = new OfflineStudyInfo();
		
		offlineStudy.setCourseSeq(getCourseSeq());
		offlineStudy.setNo(getNo());
		offlineStudy.setStudyName(getName());
		offlineStudy.setbDelete(false);
		
		return offlineStudy;
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

	public String getStartRegDate() {
		return startRegDate;
	}

	public void setStartRegDate(String startRegDate) {
		this.startRegDate = startRegDate;
	}

	public String getEndRegDate() {
		return endRegDate;
	}

	public void setEndRegDate(String endRegDate) {
		this.endRegDate = endRegDate;
	}

	public String getStartStudyDate() {
		return startStudyDate;
	}

	public void setStartStudyDate(String startStudyDate) {
		this.startStudyDate = startStudyDate;
	}

	public String getEndStudyDate() {
		return endStudyDate;
	}

	public void setEndStudyDate(String endStudyDate) {
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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}	
}
