package com.tathink.database.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.controller.model.ReqNoticeWrite;
import com.tathink.database.model.Member;
import com.tathink.database.model.Notice;
import org.springframework.data.domain.Pageable;

@Service
public class NoticeRepositoryService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	public Page<Notice> getAllPageable(int cntPerPage, int pageIdx, Sort sort)
	{
		return noticeRepository.findAll(PageRequest.of(pageIdx, cntPerPage, sort));
	}
	
	public Page<Notice> getAllByTitleContentPageable(String keyWord, Pageable sort)
	{
		return noticeRepository.findAllByTitleContent(keyWord, sort);
	}
	
	public Page<Notice> getAllByTitlePageable(String keyWord, Pageable sort)
	{
		return noticeRepository.findAllByTitle(keyWord, sort);
	}	
	
	public Page<Notice> getAllByContentPageable(String keyWord, Pageable sort)
	{
		return noticeRepository.findAllByContent(keyWord, sort);
	}		
	
	public Notice findBySeq(int seq)
	{
		return noticeRepository.findById(seq).get();
	}
	
	@Transactional
	public void removeByListNoticeSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				noticeRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		noticeRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{		
		noticeRepository.deleteById(seq);
		
		noticeRepository.flush();
	}
	
	@Transactional
	public Notice saveNotice(ReqNoticeWrite noticeInfo, Member member)
	{
		Notice notice = null;
		
		if(noticeInfo.getSeq().equals("-1"))
		{
			notice = new Notice();
			
			notice.setTitle(noticeInfo.getTitle());
			notice.setContent(noticeInfo.getEditor());
			notice.setWriter(member);
			notice.setRegDate(LocalDateTime.now());
			
			notice = noticeRepository.saveAndFlush(notice);
			
			noticeInfo.setSeq(""+notice.getSeq());
			notice.setFiles(noticeInfo.getFiles(null));
			
			noticeRepository.saveAndFlush(notice);
		}
		else
		{
			notice = noticeRepository.findById(Integer.parseInt(noticeInfo.getSeq())).get();
			notice.setTitle(noticeInfo.getTitle());
			notice.setContent(noticeInfo.getEditor());
			notice.setWriter(member);
			notice.setFiles(noticeInfo.getFiles(notice.getFiles()));
			noticeRepository.saveAndFlush(notice);
		}
		
		return notice;
		
		
	}
}
