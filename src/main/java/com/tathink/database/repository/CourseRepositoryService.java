package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Course;

@Service
public class CourseRepositoryService {
	@Autowired
	private CourseRepository courseRepository;
	
	public Course getCourse(int seq)
	{
		return courseRepository.findByCourseSeq(seq);
	}
	
	public List<Course> getAll(Sort sort)
	{
		return courseRepository.findAll(sort);
	}
	
	public List<Course> getAllByBDelete(boolean bDelete, Sort sort)
	{
		return courseRepository.findAllByBDelete(bDelete, sort);
	}
	
	public List<Course> getAllByLevelSeqBDelete(int levelSeq, boolean bDelete, Sort sort)
	{
		return courseRepository.findByLevelSeqBDelete(levelSeq, bDelete, sort);
	}
	
	public Page<Course> getAllByCourseNameBDeletePageable(boolean bDelete, String courseName,Pageable pageable)
	{
		return courseRepository.findAllByBDeleteCourseNamePageable(bDelete, courseName, pageable);
	}

	public Page<Course> getAllByCodeBDeletePageable(boolean bDelete, String courseName,Pageable pageable)
	{
		return courseRepository.findAllByBDeleteCodePageable(bDelete, courseName, pageable);
	}
	
	public Page<Course> getAllByTeacherUserNameBDeletePageable(boolean bDelete, String teacherUserName,Pageable pageable)
	{
		return courseRepository.findAllByBDeleteTeacherUsernamePageable(bDelete, teacherUserName, pageable);
	}
	
	@Transactional
	public Course AddCourse(Course course)
	{
		return courseRepository.saveAndFlush(course);
	}
	
	@Transactional
	public void DeleteCourseList(String listCourse)
	{
		String[] courselist = listCourse.split(":");
		
		for(int i=0; i<courselist.length;i++){
			
			int seq = -1;
			try{
				seq = Integer.parseInt(courselist[i]);
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			
			if(seq<0){
				return;
			}
			
			Optional<Course> course = null;
			
			course = courseRepository.findById(seq);
			
			if(course.isPresent() == false)
				return;			
						
			Course tempCourse = course.get();
				
			courseRepository.delete(tempCourse);
		}

	}
}
