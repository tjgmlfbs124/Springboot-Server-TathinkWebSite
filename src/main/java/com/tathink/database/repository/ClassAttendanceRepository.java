package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassAttendance;

public interface ClassAttendanceRepository extends JpaRepository<ClassAttendance, Integer>
{
	@Query("select attendance from ClassAttendance attendance where attendance.classSeq =:classSeq")
	List<ClassAttendance> findAllByClassSeq(@Param("classSeq")int classSeq, Sort sort);
	
	@Query("select attendance from ClassAttendance attendance where attendance.classSeq =:classSeq and attendance.username.username =:username" )
	Optional<ClassAttendance> findByClassSeqUsername(@Param("classSeq")int classSeq, @Param("username")String username);
}
