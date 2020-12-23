package com.tathink.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer>{
	@Query("select notice from Notice notice where notice.title like CONCAT('%',:keyWord,'%') or notice.content like CONCAT('%',:keyWord,'%')")
	Page<Notice> findAllByTitleContent(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select notice from Notice notice where notice.title like CONCAT('%',:keyWord,'%')")
	Page<Notice> findAllByTitle(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select notice from Notice notice where notice.content like CONCAT('%',:keyWord,'%')")
	Page<Notice> findAllByContent(@Param("keyWord")String keyWord, Pageable sort);
}
