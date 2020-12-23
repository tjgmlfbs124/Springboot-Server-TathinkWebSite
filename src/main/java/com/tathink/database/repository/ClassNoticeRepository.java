package com.tathink.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassNotice;

public interface ClassNoticeRepository extends JpaRepository<ClassNotice, Integer>{
	@Query("select notice from ClassNotice notice where notice.classSeq =:classSeq and (notice.title like CONCAT('%',:keyWord,'%') or notice.content like CONCAT('%',:keyWord,'%'))")
	Page<ClassNotice> findAllByTitleContent(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
	
	@Query("select notice from ClassNotice notice where notice.classSeq =:classSeq and notice.title like CONCAT('%',:keyWord,'%')")
	Page<ClassNotice> findAllByTitle(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
	
	@Query("select notice from ClassNotice notice where notice.classSeq =:classSeq and notice.content like CONCAT('%',:keyWord,'%')")
	Page<ClassNotice> findAllByContent(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
}
