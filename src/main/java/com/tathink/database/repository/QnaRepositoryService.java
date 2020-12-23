package com.tathink.database.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Member;
import com.tathink.database.model.Qna;

@Service
public class QnaRepositoryService {
	@Autowired
	private QnaRepository qnaRepository;
	
	public Qna findBySeq(int seq)
	{
		List<Qna> listQna = qnaRepository.findBySeq(seq);
		
		if(listQna == null || listQna.size() == 0)
			return null;
		
		return listQna.get(0);
	}
	
	public Page<Qna> getAllPageable(int cntPerPage, int pageIdx, Sort sort)
	{
		return qnaRepository.findAll(PageRequest.of(pageIdx, cntPerPage, sort));
	}
	
	public Page<Qna> getAllByQTitleQuestionAnswerQWriterStatePageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByQTitleQuestionAnswerQWriterStatePageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByQTitlePageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByQTitlePageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByQuestionPageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByQuestionPageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByAnswerPageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByAnswerPageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByQWriterPageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByQWriterPageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByStatePageable(String keyWord, Pageable sort)
	{
		return qnaRepository.findAllByStatePageable(keyWord, sort);
	}
	
	public Page<Qna> getAllByQWriterNullPageable(Pageable sort)
	{
		return qnaRepository.findAllByQWriterNullPageable(sort);
	}
	
	@Transactional
	public void saveQuestion(int seq, String qTitle, String qContent, boolean bSec, Member qWriter) throws Exception
	{
		Qna qna;
		if(seq == -1)
		{
			qna = new Qna();
			qna.setqTitle(qTitle);
			qna.setQuestion(qContent);
			qna.setbSec(bSec);
			qna.setqWriter(qWriter);
			qna.setState("답변대기");
			qna.setqRegDate(LocalDateTime.now());
			qna.setaWriter(qWriter);
			qnaRepository.save(qna);
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
			qnaRepository.save(qna);
		}
		
		
		qnaRepository.flush();
	}
	
	@Transactional
	public void saveAnswer(int seq, String answer, Member aWriter)throws Exception
	{
		Qna qna;
		
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
		qnaRepository.save(qna);
		
		qnaRepository.flush();
	}
	
	@Transactional
	public void removeByListQnaSeq(String[] listSeq)
	{		
		for(String strSeq : listSeq)
		{
			try
			{
				qnaRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		
		qnaRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{
		qnaRepository.deleteById(seq);
		qnaRepository.flush();
	}

}
