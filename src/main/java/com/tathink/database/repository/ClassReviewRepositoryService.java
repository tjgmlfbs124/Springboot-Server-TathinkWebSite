package com.tathink.database.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.ClassReview;
import com.tathink.database.model.Member;

@Service
public class ClassReviewRepositoryService {
	@Autowired
	private ClassReviewRepository classReviewRepository;
	
	public ClassReview findBySeq(int seq)
	{
		Optional<ClassReview> qna = classReviewRepository.findById(seq);
		
		if(qna.isPresent()==false)
			return null;
		
		return qna.get();
	}
	
	public List<ClassReview> getReviewListByClassSeq(int classSeq, Sort sort){
		List<ClassReview> listClassReview = null;
		listClassReview = classReviewRepository.findAllByClassSeq(classSeq, sort);
			
		return listClassReview;
	}
	
	public Page<ClassReview> getAllByContentWriterPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classReviewRepository.findAllByContentWriterPageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassReview> getAllByContentPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classReviewRepository.findAllByContentPageable(classSeq, keyWord, sort);
	}
	
	public Page<ClassReview> getAllByWriterPageable(int classSeq, String keyWord, Pageable sort)
	{
		return classReviewRepository.findAllByWriterPageable(classSeq, keyWord, sort);
	}	
	
	@Transactional
	public void saveReview(int seq, int classSeq, int reviewPoint, String reviewContent, Member reviewWriter) throws Exception
	{
		ClassReview review;
		
		if(seq == -1)
		{
			review = new ClassReview();
			review.setClassSeq(classSeq);
			review.setPoint(reviewPoint);
			review.setReview(reviewContent);
			review.setRegDate(LocalDateTime.now());
			review.setWriter(reviewWriter);
			classReviewRepository.save(review);
		}
		else
		{
			review = this.findBySeq(seq);
			
			if(review == null)
				throw new Exception("해당 게시글이 존재하지 않습니다.");
			
			if(!review.getWriter().getUsername().equals(reviewWriter.getUsername()))
				throw new Exception("해당 글의 작성자와 일치하지 않습니다.");
			
			review.setPoint(reviewPoint);
			review.setReview(reviewContent);
			classReviewRepository.save(review);
		}
				
		classReviewRepository.flush();
	}
		
	@Transactional
	public void removeByListClassReviewSeq(String[] listSeq)
	{		
		for(String strSeq : listSeq)
		{
			try
			{
				classReviewRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		
		classReviewRepository.flush();
	}
	
	@Transactional
	public void removeClassReviewBySeq(int seq)
	{
		classReviewRepository.deleteById(seq);
		classReviewRepository.flush();
	}

}
