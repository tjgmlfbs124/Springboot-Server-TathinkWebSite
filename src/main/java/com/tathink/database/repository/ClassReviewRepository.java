package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.ClassReview;

public interface ClassReviewRepository extends JpaRepository<ClassReview, Integer>{
	@Query("select review from ClassReview review where review.classSeq =:classSeq and (review.review like CONCAT('%',:keyWord,'%') or review.writer like CONCAT('%',:keyWord,'%'))")
	Page<ClassReview> findAllByContentWriterPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select review from ClassReview review where review.classSeq =:classSeq and review.review like CONCAT('%',:keyWord,'%')")
	Page<ClassReview> findAllByContentPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select review from ClassReview review where review.classSeq =:classSeq and review.writer like CONCAT('%',:keyWord,'%')")
	Page<ClassReview> findAllByWriterPageable(@Param("classSeq")int classSeq, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select review from ClassReview review where review.classSeq =:classSeq")
	List<ClassReview> findAllByClassSeq(@Param("classSeq")int classSeq, Sort sort);	
}
