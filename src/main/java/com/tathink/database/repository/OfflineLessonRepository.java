package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.OfflineLesson;

public interface OfflineLessonRepository extends JpaRepository<OfflineLesson, Integer>
{
	@Query("select lesson from OfflineLesson lesson where lesson.classSeq =:classSeq")
	List<OfflineLesson> findAllByOfflineClassSeq(@Param("classSeq")int classSeq, Sort sort);
	
	@Query("select info from OfflineLesson info where info.classSeq =:classSeq and info.seq =:lessonSeq " )
	OfflineLesson findByClassSeqLessonSeq(@Param("classSeq")int classSeq, @Param("lessonSeq")int lessonSeq);
}
