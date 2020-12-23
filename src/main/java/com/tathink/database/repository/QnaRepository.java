package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.Qna;

public interface QnaRepository extends JpaRepository<Qna, Integer>{
	@Query("select qna from Qna qna where qna.qTitle like CONCAT('%',:keyWord,'%') or qna.question like CONCAT('%',:keyWord,'%') or qna.answer like CONCAT('%',:keyWord,'%') or qna.qWriter.username like CONCAT('%',:keyWord,'%') or qna.state like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByQTitleQuestionAnswerQWriterStatePageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.qTitle like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByQTitlePageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.question like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByQuestionPageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.answer like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByAnswerPageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.qWriter.username like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByQWriterPageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.state like CONCAT('%',:keyWord,'%')")
	Page<Qna> findAllByStatePageable(@Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from Qna qna where qna.qWriter=null")
	Page<Qna> findAllByQWriterNullPageable(Pageable sort);
	
	@Query("select qna from Qna qna where qna.seq=:seq")
	List<Qna> findBySeq(@Param("seq")int keyWord);
}
