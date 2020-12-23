package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.tathink.database.model.ClassAttendance;
import com.tathink.database.model.OfflineClassMember;
import com.tathink.database.model.OfflineLesson;

public class ViewClassAttendance
{	
	private List<ClassAttendance> listClassAttendance = new ArrayList<ClassAttendance>();
	private List<OfflineLesson> listOfflineLesson = new ArrayList<OfflineLesson>();
	private List<OfflineClassMember> listOfflineClassMember = new ArrayList<OfflineClassMember>();
	private String error;
	
	public ViewClassAttendance(String error){
		this.error = error;
	}
	
	public ViewClassAttendance() {
		this.error = null;
	}

	public List<ClassAttendance> getListClassAttendance() {
		return listClassAttendance;
	}

	public void setListClassAttendance(List<ClassAttendance> listClassAttendance) {
		this.listClassAttendance = listClassAttendance;
	}

	public List<OfflineLesson> getListOfflineLesson() {
		return listOfflineLesson;
	}

	public void setListOfflineLesson(List<OfflineLesson> listOfflineLesson) {
		this.listOfflineLesson = listOfflineLesson;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<OfflineClassMember> getListOfflineClassMember() {
		return listOfflineClassMember;
	}

	public void setListOfflineClassMember(List<OfflineClassMember> listOfflineClassMember) {
		this.listOfflineClassMember = listOfflineClassMember;
	}

}
