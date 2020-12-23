package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.tathink.database.model.Member;
import com.tathink.database.model.OnlineStudyInfo;

public class ViewOnlineStudyInfo 
{
	public class Item
	{
		private OnlineStudyInfo onlineStudyInfo;
		private Member teacher;
		
		public OnlineStudyInfo getOnlineStudyInfo() {
			return onlineStudyInfo;
		}
		public void setOnlineStudyInfo(OnlineStudyInfo onlineStudyInfo) {
			this.onlineStudyInfo = onlineStudyInfo;
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
	
	public void AddOnlineStudyInfoData(OnlineStudyInfo onlineStudyInfo, Member teacher)
	{
		Item item = new Item();
		
		item.setOnlineStudyInfo(onlineStudyInfo);
		item.setTeacher(teacher);
		
		listStudyInfo.add(item);
	}
}
