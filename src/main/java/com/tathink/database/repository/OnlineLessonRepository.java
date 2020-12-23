package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tathink.database.model.OnlineLesson;

public interface OnlineLessonRepository extends JpaRepository<OnlineLesson, Integer>
{
	@Query("select lesson from OnlineLesson lesson where lesson.studyInfoSeq =:studyInfoSeq")
	List<OnlineLesson> findAllByOnlineStudyInfoSeq(@Param("studyInfoSeq")int studyInfoSeq, Sort sort);
}
