package com.tathink.database.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Faq;

@Service
public class FaqRepositoryService {
	@Autowired
	private FaqRepository faqRepository;
	
	public Page<Faq> getAllByKeyWordPageable(String keyWord, Pageable sort)
	{
		return faqRepository.getAllByKeyWordPageable(keyWord, sort);
	}
	
	@Transactional
	public void removeByListFaqSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				faqRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}			
		}
		
		faqRepository.flush();
	}
	
	@Transactional
	public void saveFaq(String seq, String qContent, String aContent)
	{
		Faq faq = null;
		if(seq.equals("-1"))
		{
			faq = new Faq();
			faq.setqContent(qContent);
			faq.setaContent(aContent);
		}
		else
		{
			faq = faqRepository.findById(Integer.parseInt(seq)).get();
			faq.setqContent(qContent);
			faq.setaContent(aContent);
		}
		
		faqRepository.saveAndFlush(faq);
	}

}
