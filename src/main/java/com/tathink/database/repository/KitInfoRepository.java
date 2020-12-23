package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.KitInfo;

public interface KitInfoRepository extends JpaRepository<KitInfo, Integer>{
	
	@Query("select info from KitInfo info where info.courseSeq =:courseSeq and info.kitSeq =:kitSeq " )
	KitInfo findByCourseSeqKitSeq(@Param("courseSeq")int courseSeq,@Param("kitSeq")int kitSeq);
	
	@Query("select info from KitInfo info where info.courseSeq =:courseSeq")
	List<KitInfo> findByCourseSeq(@Param("courseSeq")int courseSeq);
}
