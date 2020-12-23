package com.tathink.database.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.QuestionGroupEdu;

public interface QuestionGroupEduRepository extends JpaRepository<QuestionGroupEdu, Integer>{
	@Query("select qge from QuestionGroupEdu qge where qge.title like CONCAT('%',:keyWord,'%') or qge.state like CONCAT('%',:keyWord,'%')")
	Page<QuestionGroupEdu> findAllByTitleStatePageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qge from QuestionGroupEdu qge where qge.title like CONCAT('%',:keyWord,'%')")
	Page<QuestionGroupEdu> findAllByTitlePageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qge from QuestionGroupEdu qge where qge.content like CONCAT('%',:keyWord,'%')")
	Page<QuestionGroupEdu> findAllByContentPageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qge from QuestionGroupEdu qge where qge.state like CONCAT('%',:keyWord,'%')")
	Page<QuestionGroupEdu> findAllByStatePageable(@Param("keyWord")String keyWord, Pageable sort);
}
