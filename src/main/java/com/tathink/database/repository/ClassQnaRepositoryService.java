package com.tathink.database.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.ClassQna;
import com.tathink.database.model.Member;

@Service
public class ClassQnaRepositoryService {
	@Autowired
	private ClassQnaRepository classQnaRepository;
	
	public ClassQna findBySeq(int seq)
	{
		Optional<ClassQna> listQna = classQnaRepository.findById(seq);
		
		if(listQna.isPresent()==false)
			return null;
		
		return listQna.get();
	}
	
	public Page<ClassQna> getAllPageable(int cntPerPage, int pageIdx, Sort sort)
	{
		return classQnaRepository.findAll(PageRequest.of(pageIdx, cntPerPage, sort));
	}
	
	public Page<ClassQna> getAllByQTitleQuestionAnswerQWriterStatePageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByQTitleQuestionAnswerQWriterStatePageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByQTitlePageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByQTitlePageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByQuestionPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByQuestionPageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByAnswerPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByAnswerPageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByQWriterPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByQWriterPageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByStatePageable(int classSeq, String keyWord, Pageable sort)
	{
		return classQnaRepository.findAllByStatePageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassQna> getAllByQWriterNullPageable(int classSeq, Pageable sort)
	{
		return classQnaRepository.findAllByQWriterNullPageable(classSeq, sort);
	}
	
	@Transactional
	public void saveQuestion(int seq, int classSeq, String qTitle, String qContent, boolean bSec, Member qWriter) throws Exception
	{
		ClassQna qna;
		if(seq == -1)
		{
			qna = new ClassQna();
			qna.setClassSeq(classSeq);
			qna.setqTitle(qTitle);
			qna.setQuestion(qContent);
			qna.setbSec(bSec);
			qna.setqWriter(qWriter);
			qna.setState("답변대기");
			qna.setqRegDate(LocalDateTime.now());
			qna.setaWriter(qWriter);
			classQnaRepository.save(qna);
		}
		else
		{
			qna = this.findBySeq(seq);
			
			if(qna == null)
				throw new Exception("해당 게시글이 존재하지 않습니다.");
			
			if(!qna.getqWriter().getUsername().equals(qWriter.getUsername()))
				throw new Exception("해당 글의 작성자와 일치하지 않습니다.");
			
			qna.setqTitle(qTitle);
			qna.setQuestion(qContent);
			qna.setbSec(bSec);
			classQnaRepository.save(qna);
		}
		
		
		classQnaRepository.flush();
	}
	
	@Transactional
	public void saveAnswer(int seq, String answer, Member aWriter)throws Exception
	{
		ClassQna qna;
		
		qna = this.findBySeq(seq);
			
		if(qna == null)
			throw new Exception("해당 게시글이 존재하지 않습니다.");
			
		if(answer == null || answer.equals("<p>&nbsp;</p>"))
		{
			qna.setAnswer(null);
			qna.setState("답변대기");
		}
		else
		{
			qna.setAnswer(answer);
			qna.setaWriter(aWriter);
			qna.setState("답변완료");
		}
		classQnaRepository.save(qna);
		
		classQnaRepository.flush();
	}
	
	@Transactional
	public void removeByListClassQnaSeq(String[] listSeq)
	{		
		for(String strSeq : listSeq)
		{
			try
			{
				classQnaRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		
		classQnaRepository.flush();
	}
	
	@Transactional
	public void removeClassQnaBySeq(int seq)
	{
		classQnaRepository.deleteById(seq);
		classQnaRepository.flush();
	}

}
