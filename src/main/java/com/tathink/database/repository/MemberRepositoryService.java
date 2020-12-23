package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Member;

@Service
public class MemberRepositoryService {
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> getAllTeacher()
	{
		List<Member> listTeacher = null;
		
		return listTeacher;
	}
	
	public List<Member> getAll(Sort sort)
	{
		return memberRepository.findAll(sort);
	}
	
	public List<Member> getAllByBDelete(boolean bDelete, Sort sort)
	{
		return memberRepository.findAllByBDelete(bDelete, sort);
	}
	
	public List<Member> getAllByLevel(String level, Sort sort)
	{
		return memberRepository.findAllByLevel(level, sort);
	}
	
	public List<Member> getAllByLevelBDelete(boolean bDelete, String level, Sort sort)
	{
		return memberRepository.findAllByBDeleteLevel(bDelete, level, sort);
	}
	
	public Page<Member> getAllByLevelRealNameBDeletePageable(boolean bDelete, String level, String realName,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelRealNamePageable(bDelete, level, realName, pageable);
	}
	
	public Page<Member> getAllByLevelUserNameBDeletePageable(boolean bDelete, String level, String userName,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelUserNamePageable(bDelete, level, userName, pageable);
	}
	
	public Optional<Member> findById(String username)
	{
		return memberRepository.findById(username);
	}
	
	public List<Member> findByNameEmailBDelete(String realName, String email, boolean isDelete, Sort sort)
	{
		return memberRepository.findByNameEmailBDelete(realName, email, isDelete, sort);
	}
	
	public Member findByIdBDelete(String username, boolean isDelete)
	{
		Optional<Member> member = null;
		
		member = memberRepository.findById(username);
		
		if(member.isPresent() == false)
			return null;
		
		return member.get();
	}
	
	public Page<Member> getAllByUsernameRealNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteUserNameRealNamePageable(bDelete, keyword, pageable);
	}
	
	public Page<Member> getAllByRealNameBDeletePageable(boolean bDelete, String realName,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteRealNamePageable(bDelete, realName, pageable);
	}
	
	public Page<Member> getAllByUserNameBDeletePageable(boolean bDelete, String userName,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteUserNamePageable(bDelete, userName, pageable);
	}
		
	public Page<Member> getAllByTeacherRealNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelRealNamePageable(bDelete, "LEVEL_TEACHER", "LEVEL_ADMIN", keyword, pageable);
	}
	
	public Page<Member> getAllByTeacherUserNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelUserNamePageable(bDelete, "LEVEL_TEACHER", "LEVEL_ADMIN", keyword, pageable);
	}
	
	public Page<Member> getAllByTeacherNumberBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelMobilePageable(bDelete, "LEVEL_TEACHER", "LEVEL_ADMIN", keyword, pageable);
	}
	
	public Page<Member> getAllByClassAddMemberRealNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelRealNamePageable(bDelete, "LEVEL_MEMBER", keyword, pageable);
	}
	
	public Page<Member> getAllByClassAddMemberUserNameBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelUserNamePageable(bDelete, "LEVEL_MEMBER", keyword, pageable);
	}
	
	public Page<Member> getAllByClassAddMemberNumberBDeletePageable(boolean bDelete, String keyword,Pageable pageable)
	{
		return memberRepository.findAllByBDeleteLevelMobilePageable(bDelete, "LEVEL_MEMBER", keyword, pageable);
	}
	
	@Transactional
	public Member AddMember(Member member)
	{
		return memberRepository.saveAndFlush(member);
	}
	
	@Transactional
	public Member UpdateMember(Member member)
	{
		return memberRepository.saveAndFlush(member);
	}
	
	@Transactional
	public void updateMemberLevel(String listUsername, String option)
	{
		String[] userlist = listUsername.split(":");
		
		for(int i=0; i<userlist.length;i++){
			
			Optional<Member> member = null;
			
			member = memberRepository.findById(userlist[i]);			
			
			if(member.isPresent() == false)
				return;
			
			Member tempMember = member.get();
			
			tempMember.setLevel(option);
			
			
			memberRepository.saveAndFlush(tempMember);
		}

	}		
}
