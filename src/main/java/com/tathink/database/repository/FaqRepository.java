package com.tathink.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.Faq;

public interface FaqRepository extends JpaRepository<Faq, Integer>{
	@Query("select faq from Faq faq where faq.qContent like CONCAT('%',:keyWord,'%') or faq.aContent like CONCAT('%',:keyWord,'%')")
	Page<Faq> getAllByKeyWordPageable( @Param("keyWord")String keyWord, Pageable sort);
}
