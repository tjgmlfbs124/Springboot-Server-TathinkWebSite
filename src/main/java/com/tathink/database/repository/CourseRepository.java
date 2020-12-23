package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tathink.database.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	@Query("select course from Course course where course.seq =:seq")
	Course findByCourseSeq(@Param("seq")int seq);
	
	@Query("select course from Course course where course.bDelete =:bDelete")
	List<Course> findAllByBDelete(@Param("bDelete")boolean bDelete, Sort sort);
	
	@Query("select course from Course course where course.bDelete =:bDelete and course.levelSeq =:levelSeq")
	List<Course> findByLevelSeqBDelete(@Param("levelSeq")int levelSeq, @Param("bDelete")boolean bDelete, Sort sort);
		
	@Query("select course from Course course where course.bDelete=:bDelete and course.courseName like CONCAT('%',:courseName,'%')")
	Page<Course> findAllByBDeleteCourseNamePageable(@Param("bDelete")boolean bDelete, @Param("courseName")String courseName, Pageable sort);

	@Query("select course from Course course where course.bDelete=:bDelete and course.courseCode like CONCAT('%',:courseCode,'%')")
	Page<Course> findAllByBDeleteCodePageable(@Param("bDelete")boolean bDelete, @Param("courseCode")String courseCode, Pageable sort);
	
	@Query("select course from Course course where course.bDelete=:bDelete and course.teacherUsername like CONCAT('%',:teacherUsername,'%')")
	Page<Course> findAllByBDeleteTeacherUsernamePageable(@Param("bDelete")boolean bDelete, @Param("teacherUsername")String teacherUsername, Pageable sort);
	
}
