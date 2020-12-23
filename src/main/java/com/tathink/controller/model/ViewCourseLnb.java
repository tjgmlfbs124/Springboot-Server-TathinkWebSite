package com.tathink.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.tathink.database.model.Course;
import com.tathink.database.model.CourseLevel;

public class ViewCourseLnb {
	public class Item
	{
		public CourseLevel courseLevel;
		public List<Course> listCourse = new ArrayList<Course>();
		
		public Item(CourseLevel courseLevel)
		{
			this.courseLevel = courseLevel;
		}

		public CourseLevel getCourseLevel() {
			return courseLevel;
		}

		public void setCourseLevel(CourseLevel courseLevel) {
			this.courseLevel = courseLevel;
		}

		public List<Course> getListCourse() {
			return listCourse;
		}

		public void setListCourse(List<Course> listCourse) {
			this.listCourse = listCourse;
		}
		
		
	}
	
	private List<Item> listCourseLevel = new ArrayList<Item>();

	public List<Item> getListCourseLevel() {
		return listCourseLevel;
	}

	public void setListCourseLevel(List<Item> listCourseLevel) {
		this.listCourseLevel = listCourseLevel;
	}
	
	public void AddCourse(CourseLevel courseLevel, Course course)
	{
		for(Item courselevelData : listCourseLevel)
		{
			if(courselevelData.courseLevel.getSeq() == courseLevel.getSeq())
			{
				courselevelData.listCourse.add(course);
			}
		}
	}
	
	public void AddLevel(CourseLevel courseLevel)
	{
		listCourseLevel.add(new Item(courseLevel));
	}
}
