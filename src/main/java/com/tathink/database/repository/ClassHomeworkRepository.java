package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassHomework;

public interface ClassHomeworkRepository extends JpaRepository<ClassHomework, Integer>{
	@Query("select work from ClassHomework work where work.classSeq =:classSeq and (work.title like CONCAT('%',:keyWord,'%') or work.content like CONCAT('%',:keyWord,'%'))")
	Page<ClassHomework> findAllByTitleContent(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
	
	@Query("select work from ClassHomework work where work.classSeq =:classSeq and work.title like CONCAT('%',:keyWord,'%')")
	Page<ClassHomework> findAllByTitle(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
	
	@Query("select work from ClassHomework work where work.classSeq =:classSeq and work.content like CONCAT('%',:keyWord,'%')")
	Page<ClassHomework> findAllByContent(@Param("keyWord")String keyWord, @Param("classSeq")int classSeq, Pageable sort);
	
	@Query("select work from ClassHomework work where work.classSeq =:classSeq")
	List<ClassHomework> findAllByClassSeq(@Param("classSeq")int classSeq, Sort sort);
}
