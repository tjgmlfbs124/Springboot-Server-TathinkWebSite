package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.OnlineStudyInfo;

public interface OnlineStudyInfoRepository extends JpaRepository<OnlineStudyInfo, Integer>
{
	@Query("select info from OnlineStudyInfo info where info.bDelete =:bDelete and info.courseSeq =:courseSeq")
	List<OnlineStudyInfo> findByCourseSeqBDelete(@Param("courseSeq")int courseSeq, @Param("bDelete")boolean bDelete, Sort sort);
}
