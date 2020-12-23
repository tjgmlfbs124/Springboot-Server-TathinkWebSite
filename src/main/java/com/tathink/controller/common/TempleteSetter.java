package com.tathink.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.tathink.controller.model.ViewCourseLnb;
import com.tathink.database.model.Course;
import com.tathink.database.model.CourseLevel;
import com.tathink.database.model.Member;
import com.tathink.database.repository.CourseLevelRepositoryService;
import com.tathink.database.repository.CourseRepositoryService;
import com.tathink.database.repository.MemberRepositoryService;

@Component
public class TempleteSetter {
	
	@Autowired
	CourseLevelRepositoryService courseLevelRepositoryService;
	
	@Autowired
	CourseRepositoryService courseRepositoryService;
	
	@Autowired
	MemberRepositoryService memberRepositoryService;
	
	public void setHeader(Member member, Model model)
	{	
		System.out.println("header");

		List<CourseLevel> listCourseLevel = courseLevelRepositoryService.getAll();
		
		model.addAttribute("member", member);
		model.addAttribute("listCourseLevel", listCourseLevel);
	}
	
	public Course setCourseLnb(String levelSeq, String courseSeq, Model model)
	{	
		System.out.println("setCourseLnb");

		Course selectedCourse = null;
		List<CourseLevel> listLevel = courseLevelRepositoryService.getAll();
		ViewCourseLnb courseLnbData = new ViewCourseLnb();
		
		for(CourseLevel courseLevel : listLevel)
		{

			courseLnbData.AddLevel(courseLevel);
			List<Course> listCourse = courseRepositoryService.getAllByLevelSeqBDelete(courseLevel.getSeq(), false, new Sort(Direction.ASC, "priority"));
			
			for(Course course : listCourse)
			{
				courseLnbData.AddCourse(courseLevel, course);
				
				if(courseSeq == null && levelSeq != null && selectedCourse == null && courseLevel.getSeq() == Integer.parseInt(levelSeq))
				{
					selectedCourse = course;
				}
				
				if(courseSeq != null && levelSeq == null && course.getSeq() == Integer.parseInt(courseSeq))
				{
					selectedCourse = course;
				}
				
				if(levelSeq == null && courseSeq == null && selectedCourse == null)
				{
					selectedCourse = course;
				}
			}
			
		}
		
		model.addAttribute("courseLnbData", courseLnbData);
		
		return selectedCourse;
	}
}
