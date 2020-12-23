package com.tathink.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.Kit;

public interface KitRepository extends JpaRepository<Kit, Integer>{
	
	@Query("select kit from Kit kit where kit.bDelete=:bDelete and (kit.code like CONCAT('%',:keyWord,'%') or kit.name like CONCAT('%',:keyWord,'%'))")
	Page<Kit> findAllByBDeleteCodeNamePageable(@Param("bDelete")boolean bDelete, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select kit from Kit kit where kit.bDelete=:bDelete and kit.code like CONCAT('%',:code,'%')")
	Page<Kit> findAllByBDeleteCodePageable(@Param("bDelete")boolean bDelete, @Param("code")String code, Pageable sort);
	
	@Query("select kit from Kit kit where kit.bDelete=:bDelete and kit.name like CONCAT('%',:name,'%')")
	Page<Kit> findAllByBDeleteNamePageable(@Param("bDelete")boolean bDelete, @Param("name")String name, Pageable sort);

}
