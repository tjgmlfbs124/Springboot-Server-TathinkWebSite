package com.tathink.database.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.OfflineClass;

public interface OfflineClassRepository extends JpaRepository<OfflineClass, Integer>
{	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.offlineStudyInfoSeq =:studyInfoSeq and info.startRegDate <=:date and info.endRegDate >=:date")
	List<OfflineClass> findActiveClassByOfflineStudyInfoSeq(@Param("studyInfoSeq")int studyInfoSeq, @Param("date")LocalDate date, @Param("bDelete")boolean bDelete, Sort sort);	

	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.teacherUsername =:teacherUsername")
	List<OfflineClass> findClassListByTeacherUsername(@Param("teacherUsername")String teacherUsername, @Param("bDelete")boolean bDelete, Sort sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.seq in (:listSeq)")
	List<OfflineClass> findClassListBySeqList(@Param("listSeq")List<Integer> listSeq, @Param("bDelete")boolean bDelete, Sort sort);	
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findAllClassByBDeletePageable(@Param("bDelete")boolean bDelete, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);	

	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.startRegDate >:date and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findWaitClassByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.startRegDate <=:date and info.endRegDate >=:date and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findApplyClassByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.startStudyDate <=:date and info.endStudyDate >=:date and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findStartClassByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.endStudyDate < :date and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findEndClassByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.seq in (:listSeq) and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findMyClassAllByBDeletePageable(@Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.seq in (:listSeq) and (info.courseCode like CONCAT('%',:name,'%') or info.courseName like CONCAT('%',:name,'%') or info.code like CONCAT('%',:name,'%') or info.name like CONCAT('%',:name,'%') or info.teacherRealname like CONCAT('%',:name,'%'))")
	Page<OfflineClass> findMyClassAllByTypeBDeletePageable(@Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("name")String name, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and ((info.startRegDate <=:date and info.endRegDate >=:date) or (info.endRegDate <=:date and info.startStudyDate >:date)) and info.seq in (:listSeq) and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findMyClassApplyByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.endRegDate <=:date and info.startStudyDate >:date and info.seq in (:listSeq) and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findMyClassWaitByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.startStudyDate <=:date and info.endStudyDate >=:date and info.seq in (:listSeq) and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findMyClassStartByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.endStudyDate <:date and info.seq in (:listSeq) and info.courseCode like CONCAT('%',:coursecode,'%') and info.courseName like CONCAT('%',:coursename,'%') and info.code like CONCAT('%',:code,'%') and info.name like CONCAT('%',:name,'%') and info.teacherRealname like CONCAT('%',:teacher,'%')")
	Page<OfflineClass> findMyClassEndByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("listSeq")List<Integer> listSeq, @Param("coursecode")String coursecode, @Param("coursename")String coursename, @Param("code")String code, @Param("name")String name, @Param("teacher")String teacher, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and ((info.startRegDate <=:date and info.endRegDate >=:date) or (info.endRegDate <=:date and info.startStudyDate >:date)) and info.teacherUsername=:teacher and info.name like CONCAT('%',:word,'%')")
	Page<OfflineClass> findClassApplyByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("teacher")String teacher, @Param("word")String word, Pageable sort);

	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.endRegDate <=:date and info.startStudyDate >:date and info.teacherUsername=:teacher and info.name like CONCAT('%',:word,'%')")
	Page<OfflineClass> findClassWaitByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("teacher")String teacher, @Param("word")String word, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.startStudyDate <=:date and info.endStudyDate >=:date and info.teacherUsername=:teacher and info.name like CONCAT('%',:word,'%')")
	Page<OfflineClass> findClassStartByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("teacher")String teacher, @Param("word")String word, Pageable sort);
	
	@Query("select info from OfflineClass info where info.bDelete =:bDelete and info.endStudyDate <:date and info.teacherUsername=:teacher and info.name like CONCAT('%',:word,'%')")
	Page<OfflineClass> findClassEndByBDeletePageable(@Param("date")LocalDate date, @Param("bDelete")boolean bDelete, @Param("teacher")String teacher, @Param("word")String word, Pageable sort);

}
