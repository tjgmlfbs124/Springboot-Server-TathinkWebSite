package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tathink.database.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	@Query("select mem from Member mem where mem.bDelete =:bDelete")
	List<Member> findAllByBDelete(@Param("bDelete")boolean bDelete, Sort sort);
	
	@Query("select mem from Member mem where mem.level =:level")
	List<Member> findAllByLevel(@Param("level")String level, Sort sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.level=:level")
	List<Member> findAllByBDeleteLevel(@Param("bDelete")boolean bDelete, @Param("level")String level, Sort sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.level=:level")
	Page<Member> findAllByBDeleteLevelPageable(@Param("bDelete")boolean bDelete, @Param("level")String level, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.level=:level and mem.realname like CONCAT('%',:realname,'%')")
	Page<Member> findAllByBDeleteLevelRealNamePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("realname")String realname, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.level=:level and mem.username like CONCAT('%',:username,'%')")
	Page<Member> findAllByBDeleteLevelUserNamePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("username")String username, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.level=:level and mem.mobile like CONCAT('%',:mobile,'%')")
	Page<Member> findAllByBDeleteLevelMobilePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("mobile")String mobile, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and (mem.level=:level or mem.level=:level2) and mem.realname like CONCAT('%',:realname,'%')")
	Page<Member> findAllByBDeleteLevelRealNamePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("level2")String level2, @Param("realname")String realname, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and (mem.level=:level or mem.level=:level2) and mem.username like CONCAT('%',:username,'%')")
	Page<Member> findAllByBDeleteLevelUserNamePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("level2")String level2, @Param("username")String username, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and (mem.level=:level or mem.level=:level2) and mem.mobile like CONCAT('%',:mobile,'%')")
	Page<Member> findAllByBDeleteLevelMobilePageable(@Param("bDelete")boolean bDelete, @Param("level")String level, @Param("level2")String level2, @Param("mobile")String mobile, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and (mem.realname like CONCAT('%',:keyWord,'%') or mem.username like CONCAT('%',:keyWord,'%'))")
	Page<Member> findAllByBDeleteUserNameRealNamePageable(@Param("bDelete")boolean bDelete, @Param("keyWord")String keyWord, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.realname like CONCAT('%',:realname,'%')")
	Page<Member> findAllByBDeleteRealNamePageable(@Param("bDelete")boolean bDelete, @Param("realname")String realname, Pageable sort);
	
	@Query("select mem from Member mem where mem.bDelete=:bDelete and mem.username like CONCAT('%',:username,'%')")
	Page<Member> findAllByBDeleteUserNamePageable(@Param("bDelete")boolean bDelete, @Param("username")String username, Pageable sort);
	
	@Query("select mem from Member mem where mem.realname=:realName and mem.email=:email and mem.bDelete =:bDelete")
	List<Member> findByNameEmailBDelete(@Param("realName")String realName, @Param("email")String email, @Param("bDelete")boolean isDelete, Sort sort);		
}