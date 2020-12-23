package com.tathink.database.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tathink.controller.model.ReqClassHomeworkWrite;
import com.tathink.database.model.ClassHomework;
import com.tathink.database.model.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ClassHomeworkRepositoryService {
	@Autowired
	private ClassHomeworkRepository classHomeworkRepository;
	
	public Page<ClassHomework> getAllByTitleContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classHomeworkRepository.findAllByTitleContent(keyWord, classSeq, sort);
	}
	
	public Page<ClassHomework> getAllByTitlePageable(String keyWord, int classSeq, Pageable sort)
	{
		return classHomeworkRepository.findAllByTitle(keyWord, classSeq, sort);
	}	
	
	public Page<ClassHomework> getAllByContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classHomeworkRepository.findAllByContent(keyWord, classSeq, sort);
	}		
	
	public ClassHomework findBySeq(int seq)
	{
		return classHomeworkRepository.findById(seq).get();
	}
	
	public List<ClassHomework> getAllByClassSeq(int classSeq, Sort sort){
		return classHomeworkRepository.findAllByClassSeq(classSeq, sort);
	}
	
	@Transactional
	public void removeHomeworkByListSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				classHomeworkRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		classHomeworkRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{		
		classHomeworkRepository.deleteById(seq);
		
		classHomeworkRepository.flush();
	}
	
	@Transactional
	public ClassHomework saveHomework(ReqClassHomeworkWrite homeworkInfo, Member member)
	{
		ClassHomework homework = null;
		
		if(homeworkInfo.getSeq().equals("-1"))
		{
			homework = new ClassHomework();
			
			homework.setClassSeq(Integer.parseInt(homeworkInfo.getClassSeq()));
			homework.setTitle(homeworkInfo.getTitle());
			homework.setContent(homeworkInfo.getContent());
			homework.setApplyDate(homeworkInfo.getApplyDate());
			homework.setProcessDate(homeworkInfo.getProcessDate());
			homework.setWriter(member);
			homework.setRegDate(LocalDateTime.now());
			
			homework = classHomeworkRepository.saveAndFlush(homework);
			
			homeworkInfo.setSeq(""+homework.getSeq());
			homework.setFiles(homeworkInfo.getFiles(null));
			
			classHomeworkRepository.saveAndFlush(homework);
		}
		else
		{
			homework = classHomeworkRepository.findById(Integer.parseInt(homeworkInfo.getSeq())).get();
			
			homework.setClassSeq(Integer.parseInt(homeworkInfo.getClassSeq()));
			homework.setTitle(homeworkInfo.getTitle());
			homework.setContent(homeworkInfo.getContent());
			homework.setApplyDate(homeworkInfo.getApplyDate());
			homework.setProcessDate(homeworkInfo.getProcessDate());
			homework.setWriter(member);
			homework.setFiles(homeworkInfo.getFiles(homework.getFiles()));
			classHomeworkRepository.saveAndFlush(homework);
		}
		
		return homework;
		
		
	}
}
