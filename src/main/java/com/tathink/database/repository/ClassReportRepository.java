package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassReport;

public interface ClassReportRepository extends JpaRepository<ClassReport, Integer>{	
	@Query("select report from ClassReport report where report.classSeq =:classSeq and report.writer.username =:username")
	List<ClassReport> findAllByClassSeq(@Param("classSeq")int classSeq, @Param("username")String username, Sort sort);
	
	@Query("select report from ClassReport report where report.homeworkSeq =:homeworkSeq")
	List<ClassReport> findAllByHomeworkSeq(@Param("homeworkSeq")int homeworkSeq, Sort sort);
	
	@Query("select report from ClassReport report where report.homeworkSeq =:homeworkSeq and report.writer.username =:username")
	ClassReport findAllByHomeworkSeq(@Param("homeworkSeq")int classSeq, @Param("username")String username, Sort sort);
}
