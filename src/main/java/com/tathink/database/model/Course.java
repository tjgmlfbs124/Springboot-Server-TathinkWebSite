package com.tathink.database.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Course {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;	
	private boolean bDelete;
	
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
	public boolean isbDelete() {
		return bDelete;
	}
	public void setbDelete(boolean bDelete) {
		this.bDelete = bDelete;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getTeacherRealname() {
		return teacherRealname;
	}
	public void setTeacherRealname(String teacherRealname) {
		this.teacherRealname = teacherRealname;
	}
}
