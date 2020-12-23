package com.tathink.database.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tathink.controller.model.ReqClassNoticeWrite;
import com.tathink.database.model.ClassNotice;
import com.tathink.database.model.Member;
import org.springframework.data.domain.Pageable;

@Service
public class ClassNoticeRepositoryService {
	@Autowired
	private ClassNoticeRepository classNoticeRepository;
	
	public Page<ClassNotice> getAllByTitleContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classNoticeRepository.findAllByTitleContent(keyWord, classSeq, sort);
	}
	
	public Page<ClassNotice> getAllByTitlePageable(String keyWord, int classSeq, Pageable sort)
	{
		return classNoticeRepository.findAllByTitle(keyWord, classSeq, sort);
	}	
	
	public Page<ClassNotice> getAllByContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classNoticeRepository.findAllByContent(keyWord, classSeq, sort);
	}		
	
	public ClassNotice findBySeq(int seq)
	{
		return classNoticeRepository.findById(seq).get();
	}
	
	@Transactional
	public void removeByListNoticeSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				classNoticeRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		classNoticeRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{		
		classNoticeRepository.deleteById(seq);
		
		classNoticeRepository.flush();
	}
	
	@Transactional
	public ClassNotice saveNotice(ReqClassNoticeWrite noticeInfo, Member member)
	{
		ClassNotice notice = null;
		
		if(noticeInfo.getSeq().equals("-1"))
		{
			notice = new ClassNotice();
			
			notice.setClassSeq(Integer.parseInt(noticeInfo.getClassSeq()));
			notice.setTitle(noticeInfo.getTitle());
			notice.setContent(noticeInfo.getEditor());
			notice.setWriter(member);
			notice.setRegDate(LocalDateTime.now());
			
			notice = classNoticeRepository.saveAndFlush(notice);
			
			noticeInfo.setSeq(""+notice.getSeq());
			notice.setFiles(noticeInfo.getFiles(null));
			
			classNoticeRepository.saveAndFlush(notice);
		}
		else
		{
			notice = classNoticeRepository.findById(Integer.parseInt(noticeInfo.getSeq())).get();
			notice.setClassSeq(Integer.parseInt(noticeInfo.getClassSeq()));
			notice.setTitle(noticeInfo.getTitle());
			notice.setContent(noticeInfo.getEditor());
			notice.setWriter(member);
			notice.setFiles(noticeInfo.getFiles(notice.getFiles()));
			classNoticeRepository.saveAndFlush(notice);
		}
		
		return notice;
		
		
	}
}
