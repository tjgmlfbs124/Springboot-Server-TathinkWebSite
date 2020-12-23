package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.ClassAttendance;
import com.tathink.database.model.ClassAttendanceList;
import com.tathink.database.model.Member;

@Service
public class ClassAttendanceRepositoryService {
	@Autowired
	private ClassAttendanceRepository classAttendanceRepository;
	
	public List<ClassAttendance> getAllByClassSeq(int classSeq, Sort sort)
	{
		return  classAttendanceRepository.findAllByClassSeq(classSeq, sort);
	}
	
	public Optional<ClassAttendance> getByClassSeqUsername(int classSeq, String username)
	{
		return  classAttendanceRepository.findByClassSeqUsername(classSeq, username);
	}
	
	@Transactional
	public void saveAttendance(ClassAttendance classAttendance){
		try{
			classAttendanceRepository.saveAndFlush(classAttendance);					
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	@Transactional
	public void savelist(ClassAttendanceList checklist, Member member){
		try{
			for(int i =0; i<checklist.getCheckList().size(); i++){
				
				ClassAttendance tempCheck = new ClassAttendance();
				if(checklist.getCheckList().get(i).getSeq()>=0){
					tempCheck.setSeq(checklist.getCheckList().get(i).getSeq());
				}
				
				tempCheck.setClassSeq(checklist.getCheckList().get(i).getClassSeq());
				tempCheck.setUsername(checklist.getCheckList().get(i).getUsername());
				tempCheck.setCheckList(checklist.getCheckList().get(i).getCheckList());
				tempCheck.setContent(checklist.getCheckList().get(i).getContent());
				tempCheck.setWriter(member.getUsername());
				
				classAttendanceRepository.saveAndFlush(tempCheck);
			}			
								
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
}
