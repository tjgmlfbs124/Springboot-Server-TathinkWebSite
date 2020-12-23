package com.tathink.database.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OfflineClass {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private boolean bDelete;
	
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
	public boolean isbDelete() {
		return bDelete;
	}
	public void setbDelete(boolean bDelete) {
		this.bDelete = bDelete;
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
	public String getTeacherRealname() {
		return teacherRealname;
	}
	public void setTeacherRealname(String teacherRealname) {
		this.teacherRealname = teacherRealname;
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
}
