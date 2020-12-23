package com.tathink.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OnlineLesson {
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
	private int studyInfoSeq;
	private int lessonOrder;
	private String content;
	
	public int getSeq() 
	{
		return seq;
	}
	
	public void setSeq(int seq) 
	{
		this.seq = seq;
	}
	
	public int getStudyInfoSeq() {
		return studyInfoSeq;
	}

	public void setStudyInfoSeq(int studyInfoSeq) {
		this.studyInfoSeq = studyInfoSeq;
	}

	public int getLessonOrder() 
	{
		return lessonOrder;
	}
	
	public void setLessonOrder(int lessonOrder) 
	{
		this.lessonOrder = lessonOrder;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setContent(String content) 
	{
		this.content = content;
	}
}
