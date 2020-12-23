package com.tathink.controller.model;

import java.time.LocalDate;

import com.tathink.database.model.Course;

public class ReqCourse 
{
	private int seq = -1;
	private int levelSeq;
	private int priority;
	private String courseName;
	private String courseCode;
	private String bigImgPath;
	private String smallImgPath;
	private String teacherUsername;
	private String teacherRealname;
	private String target;
	private String term;
	private String info;
	private String needsCourse;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getLevelSeq() {
		return levelSeq;
	}
	public void setLevelSeq(int levelSeq) {
		this.levelSeq = levelSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getBigImgPath() {
		return bigImgPath;
	}
	public void setBigImgPath(String bigImgPath) {
		this.bigImgPath = bigImgPath;
	}
	public String getSmallImgPath() {
		return smallImgPath;
	}
	public void setSmallImgPath(String smallImgPath) {
		this.smallImgPath = smallImgPath;
	}
	public String getTeacherUsername() {
		return teacherUsername;
	}
	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getNeedsCourse() {
		return needsCourse;
	}
	public void setNeedsCourse(String needsCourse) {
		this.needsCourse = needsCourse;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Course convertToCourse() {
		Course course = new Course();
		course.setSeq(getSeq());
		course.setLevelSeq(getLevelSeq());
		course.setPriority(getPriority());
		course.setCourseName(getCourseName());
		course.setCourseCode(getCourseCode());
		course.setBigImgPath(getBigImgPath());
		course.setSmallImgPath(getSmallImgPath());
		course.setTeacherUsername(getTeacherUsername());
		course.setTeacherRealname(getTeacherRealname());
		course.setTarget(getTarget());
		course.setTerm(getTerm());
		course.setInfo(getInfo());
		course.setNeedsCourse(getNeedsCourse());
		course.setbDelete(false);
		course.setRegDate(LocalDate.now());
		
		return course;
	}
	public String getTeacherRealname() {
		return teacherRealname;
	}
	public void setTeacherRealname(String teacherRealname) {
		this.teacherRealname = teacherRealname;
	}
}
