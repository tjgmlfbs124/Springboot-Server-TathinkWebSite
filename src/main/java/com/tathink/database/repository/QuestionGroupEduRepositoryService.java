package com.tathink.database.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.QuestionGroupEdu;

@Service
public class QuestionGroupEduRepositoryService {
	@Autowired
	private QuestionGroupEduRepository questionGroupEduRepository;
	
	public QuestionGroupEdu findBySeq(int seq)
	{
		return questionGroupEduRepository.findById(seq).get();
	}
	
	public Page<QuestionGroupEdu> getAllPageable(int cntPerPage, int pageIdx, Sort sort)
	{
		return questionGroupEduRepository.findAll(PageRequest.of(pageIdx, cntPerPage, sort));
	}
	
	public Page<QuestionGroupEdu> getAllByTitleStatePageable(String keyWord, Pageable sort)
	{
		return questionGroupEduRepository.findAllByTitleStatePageable(keyWord, sort);
	}
	
	public Page<QuestionGroupEdu> getAllByTitlePageable(String keyWord, Pageable sort)
	{
		return questionGroupEduRepository.findAllByTitlePageable(keyWord, sort);
	}
	
	public Page<QuestionGroupEdu> getAllByContentPageable(String keyWord, Pageable sort)
	{
		return questionGroupEduRepository.findAllByContentPageable(keyWord, sort);
	}
	
	public Page<QuestionGroupEdu> getAllByStatePageable(String keyWord, Pageable sort)
	{
		return questionGroupEduRepository.findAllByStatePageable(keyWord, sort);
	}
	
	@Transactional
	public void save(int seq, String title, String companyName, String charder, String mobile01, String mobile02, String mobile03, String email01, String email02, String content) throws Exception
	{
		QuestionGroupEdu questionGroupEdu;
		
		if(seq == -1)
		{
			questionGroupEdu = new QuestionGroupEdu();
			questionGroupEdu.setTitle(title);
			questionGroupEdu.setCompanyName(companyName);
			questionGroupEdu.setCharder(charder);
			questionGroupEdu.setMobile01(mobile01);
			questionGroupEdu.setMobile02(mobile02);
			questionGroupEdu.setMobile03(mobile03);
			questionGroupEdu.setEmail01(email01);
			questionGroupEdu.setEmail02(email02);
			questionGroupEdu.setContent(content);
			questionGroupEdu.setState("답변대기");
			questionGroupEdu.setRegDate(LocalDateTime.now());
			questionGroupEduRepository.save(questionGroupEdu);
		}
		else
		{
			questionGroupEdu = questionGroupEduRepository.findById(seq).get();
			
			if(questionGroupEdu == null)
				throw new Exception("해당 게시글이 존재하지 않습니다.");
						
			questionGroupEdu.setTitle(title);
			questionGroupEdu.setCompanyName(companyName);
			questionGroupEdu.setCharder(charder);
			questionGroupEdu.setMobile01(mobile01);
			questionGroupEdu.setMobile02(mobile02);
			questionGroupEdu.setMobile03(mobile03);
			questionGroupEdu.setEmail01(email01);
			questionGroupEdu.setEmail02(email02);
			questionGroupEdu.setContent(content);
			questionGroupEduRepository.save(questionGroupEdu);
		}
		
		questionGroupEduRepository.flush();
	}
	
	@Transactional
	public void saveAnswer(int seq, boolean bAnswer)throws Exception
	{
		QuestionGroupEdu questionGroupEdu;
		
		questionGroupEdu = questionGroupEduRepository.findById(seq).get();
			
		if(questionGroupEdu == null)
			throw new Exception("해당 게시글이 존재하지 않습니다.");
			
		if(bAnswer == true)
			questionGroupEdu.setState("답변완료");
		else
			questionGroupEdu.setState("답변대기");
		
		questionGroupEduRepository.save(questionGroupEdu);
		
		questionGroupEduRepository.flush();
	}
	
	@Transactional
	public void removeByListQuestoinGroupEduSeq(String[] listSeq)
	{		
		for(String strSeq : listSeq)
		{
			try
			{
				questionGroupEduRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		questionGroupEduRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{
		questionGroupEduRepository.deleteById(seq);
		questionGroupEduRepository.flush();
	}

}
