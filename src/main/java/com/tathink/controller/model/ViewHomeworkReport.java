package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.ClassReport;
import com.tathink.database.model.Member;

public class ViewHomeworkReport
{
	public class Item
	{
		private ClassHomework classHomework;
		private ClassReport classReport;
		private Member user;
		public ClassHomework getClassHomework() {
			return classHomework;
		}
		public void setClassHomework(ClassHomework classHomework) {
			this.classHomework = classHomework;
		}
		public ClassReport getClassReport() {
			return classReport;
		}
		public void setClassReport(ClassReport classReport) {
			this.classReport = classReport;
		}
		public Member getUser() {
			return user;
		}
		public void setUser(Member user) {
			this.user = user;
		}
		
	}	
	
	private List<Item> listHomeworkInfo = new ArrayList<Item>();
	
	public List<Item> getListHomeworkInfo() {
		return listHomeworkInfo;
	}

	public void setListHomeworkInfo(List<Item> listHomeworkInfo) {
		this.listHomeworkInfo = listHomeworkInfo;
	}
	
	public void AddClassHomeworkInfoData(ClassHomework classHomework, ClassReport classReport, Member user)
	{
		Item item = new Item();
		
		item.setClassHomework(classHomework);
		item.setClassReport(classReport);	
		item.setUser(user);
		
		listHomeworkInfo.add(item);
	}
}
