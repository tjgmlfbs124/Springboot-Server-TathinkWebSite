package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.tathink.database.model.Member;
import com.tathink.database.model.OfflineClass;
import com.tathink.database.model.OfflineStudyInfo;

public class ViewOfflineStudyInfo 
{
	public class Item
	{
		private OfflineStudyInfo offlineStudyInfo;
		private OfflineClass offlineClass;
		private Member teacher;
		
		public OfflineStudyInfo getOfflineStudyInfo() {
			return offlineStudyInfo;
		}
		public void setOfflineStudyInfo(OfflineStudyInfo offlineStudyInfo) {
			this.offlineStudyInfo = offlineStudyInfo;
		}
		public OfflineClass getOfflineClass() {
			return offlineClass;
		}
		public void setOfflineClass(OfflineClass offlineClass) {
			this.offlineClass = offlineClass;
		}
		public Member getTeacher() {
			return teacher;
		}
		public void setTeacher(Member teacher) {
			this.teacher = teacher;
		}
	}	
	
	private List<Item> listStudyInfo = new ArrayList<Item>();
	
	public List<Item> getListStudyInfo() {
		return listStudyInfo;
	}

	public void setListStudyInfo(List<Item> listStudyInfo) {
		this.listStudyInfo = listStudyInfo;
	}
	
	public void AddOfflineStudyInfoData(OfflineStudyInfo offlineStudyInfo, OfflineClass offlineClass, Member teacher)
	{
		Item item = new Item();
		
		item.setOfflineStudyInfo(offlineStudyInfo);
		item.setOfflineClass(offlineClass);
		item.setTeacher(teacher);
		
		listStudyInfo.add(item);
	}
}
