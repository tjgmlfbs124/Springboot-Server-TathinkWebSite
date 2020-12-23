package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OfflineClassMember;

@Service
public class OfflineClassMemberRepositoryService {
	@Autowired
	private OfflineClassMemberRepository offlineClassMemberRepository;
	
	public List<OfflineClassMember> getClassSeqByUserName(String userName){
		return offlineClassMemberRepository.findByUserName(userName);
	}
	
	public List<OfflineClassMember> getClassSeqByUserName(String userName, Sort sort){
		return offlineClassMemberRepository.findByUserName(userName, sort);
	}
	
	public List<OfflineClassMember> getClassMemberListByClassSeq(int classSeq, Sort sort){
		return offlineClassMemberRepository.findByClassSeq(classSeq, sort);
	}
	
	public OfflineClassMember getByClassSeqUserName(int classSeq, String userName){		
		return offlineClassMemberRepository.findByClassSeqUserName(classSeq, userName);
	}	
	
	public Page<OfflineClassMember> getAllByClassMemberRealNameUserNameBDeletePageable(int classSeq, String name,Pageable pageable)
	{
		return offlineClassMemberRepository.findAllByClassMemberRealNameUserNamePageable( classSeq, name, pageable);
	}
			
	public Page<OfflineClassMember> getAllByClassMemberRealNameBDeletePageable(int classSeq, String realname,Pageable pageable)
	{
		return offlineClassMemberRepository.findAllByClassMemberRealNamePageable( classSeq, realname, pageable);
	}
	
	public Page<OfflineClassMember> getAllByClassMemberUserNameBDeletePageable(int classSeq, String username,Pageable pageable)
	{
		return offlineClassMemberRepository.findAllByClassMemberUserNamePageable(classSeq, username, pageable);
	}
	
	public Page<OfflineClassMember> getAllByClassMemberMobileBDeletePageable(int classSeq, String mobile,Pageable pageable)
	{
		return offlineClassMemberRepository.findAllByClassMemberMobilePageable(classSeq, mobile, pageable);
	}
		
	@Transactional
	public OfflineClassMember saveOfflinClassMember(OfflineClassMember classMemberinfo){
				
		return offlineClassMemberRepository.saveAndFlush(classMemberinfo);
	}
	
	@Transactional
	public void deleteOfflinClass(int classSeq, String listMember){
		
		String[] memberlist = listMember.split(":");
		
		for(int i=0; i<memberlist.length;i++){	
			
			int seq = -1;
			try{
				seq = Integer.parseInt(memberlist[i]);
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			
			if(seq<0){
				return;
			}

			Optional<OfflineClassMember> offlineClassMemeber = null;				
			offlineClassMemeber = offlineClassMemberRepository.findById(seq);
			
			if(offlineClassMemeber.isPresent()==false)
				return;
			OfflineClassMember tempMember =  offlineClassMemeber.get();
			offlineClassMemberRepository.delete(tempMember);
		}
	}
}
