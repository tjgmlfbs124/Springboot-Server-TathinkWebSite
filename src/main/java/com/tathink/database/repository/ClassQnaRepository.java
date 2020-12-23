package com.tathink.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassQna;

public interface ClassQnaRepository extends JpaRepository<ClassQna, Integer>{
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.qTitle like CONCAT('%',:keyWord,'%') or qna.question like CONCAT('%',:keyWord,'%') or qna.answer like CONCAT('%',:keyWord,'%') or qna.qWriter.username like CONCAT('%',:keyWord,'%') or qna.state like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByQTitleQuestionAnswerQWriterStatePageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.qTitle like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByQTitlePageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.question like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByQuestionPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.answer like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByAnswerPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.qWriter.username like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByQWriterPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and qna.state like CONCAT('%',:keyWord,'%')")
	Page<ClassQna> findAllByStatePageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select qna from ClassQna qna where qna.classSeq =:classSeq and  qna.qWriter=null")
	Page<ClassQna> findAllByQWriterNullPageable(@Param("classSeq")int classSeq, Pageable sort);	
}
