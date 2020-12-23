package com.tathink.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KitInfo {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
	private int courseSeq;
	private int kitSeq;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(int courseSeq) {
		this.courseSeq = courseSeq;
	}
	public int getKitSeq() {
		return kitSeq;
	}
	public void setKitSeq(int kitSeq) {
		this.kitSeq = kitSeq;
	}
}
