package com.tathink.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.CourseLevel;

public interface CourseLevelRepository extends JpaRepository<CourseLevel, Integer> {

	@Query("select levelName from CourseLevel courseLevel where courseLevel.seq =:seq")
	String findLevelNameByLevelSeq(@Param("seq")int seq);
}
