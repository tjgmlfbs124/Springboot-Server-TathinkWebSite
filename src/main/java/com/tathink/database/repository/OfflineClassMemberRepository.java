package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tathink.database.model.OfflineClassMember;

public interface OfflineClassMemberRepository extends JpaRepository<OfflineClassMember, Integer>
{	
	@Query("select mem from OfflineClassMember mem where mem.username=:username")
	List<OfflineClassMember> findByUserName( @Param("username")String username);
	
	@Query("select mem from OfflineClassMember mem where mem.username=:username")
	List<OfflineClassMember> findByUserName( @Param("username")String username, Sort sort);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq")
	List<OfflineClassMember> findByClassSeq( @Param("classSeq")int classSeq, Sort sort);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq and mem.username=:username")
	OfflineClassMember findByClassSeqUserName( @Param("classSeq")int classSeq, @Param("username")String username);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq and (mem.realname like CONCAT('%',:name,'%') or mem.username like CONCAT('%',:name,'%') )")
	Page<OfflineClassMember> findAllByClassMemberRealNameUserNamePageable(@Param("classSeq")int classSeq, @Param("name")String name, Pageable sort);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq and mem.realname like CONCAT('%',:realname,'%')")
	Page<OfflineClassMember> findAllByClassMemberRealNamePageable(@Param("classSeq")int classSeq, @Param("realname")String realname, Pageable sort);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq and mem.username like CONCAT('%',:username,'%')")
	Page<OfflineClassMember> findAllByClassMemberUserNamePageable(@Param("classSeq")int classSeq, @Param("username")String username, Pageable sort);
	
	@Query("select mem from OfflineClassMember mem where mem.classSeq=:classSeq and mem.mobile like CONCAT('%',:mobile,'%')")
	Page<OfflineClassMember> findAllByClassMemberMobilePageable(@Param("classSeq")int classSeq, @Param("mobile")String mobile, Pageable sort);
}
