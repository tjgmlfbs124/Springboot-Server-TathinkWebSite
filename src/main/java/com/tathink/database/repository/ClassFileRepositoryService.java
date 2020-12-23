package com.tathink.database.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tathink.controller.model.ReqClassFileWrite;
import com.tathink.database.model.ClassFile;
import com.tathink.database.model.Member;
import org.springframework.data.domain.Pageable;

@Service
public class ClassFileRepositoryService {
	@Autowired
	private ClassFileRepository classFileRepository;
	
	public Page<ClassFile> getAllByTitleContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classFileRepository.findAllByTitleContent(keyWord, classSeq, sort);
	}
	
	public Page<ClassFile> getAllByTitlePageable(String keyWord, int classSeq, Pageable sort)
	{
		return classFileRepository.findAllByTitle(keyWord, classSeq, sort);
	}	
	
	public Page<ClassFile> getAllByContentPageable(String keyWord, int classSeq, Pageable sort)
	{
		return classFileRepository.findAllByContent(keyWord, classSeq, sort);
	}		
	
	public ClassFile findBySeq(int seq)
	{
		return classFileRepository.findById(seq).get();
	}
	
	@Transactional
	public void removeByListFileSeq(String[] listSeqList)
	{
		for(String strSeq : listSeqList)
		{
			try
			{
				classFileRepository.deleteById(Integer.parseInt(strSeq));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
		}
		classFileRepository.flush();
	}
	
	@Transactional
	public void removeBySeq(int seq)
	{		
		classFileRepository.deleteById(seq);
		
		classFileRepository.flush();
	}
	
	@Transactional
	public ClassFile saveFile(ReqClassFileWrite fileInfo, Member member)
	{
		ClassFile tempFile = null;
		
		if(fileInfo.getSeq().equals("-1"))
		{
			tempFile = new ClassFile();
			
			tempFile.setClassSeq(Integer.parseInt(fileInfo.getClassSeq()));
			tempFile.setTitle(fileInfo.getTitle());
			tempFile.setContent(fileInfo.getEditor());
			tempFile.setWriter(member);
			tempFile.setRegDate(LocalDateTime.now());
			
			tempFile = classFileRepository.saveAndFlush(tempFile);
			
			fileInfo.setSeq(""+tempFile.getSeq());
			tempFile.setFiles(fileInfo.getFiles(null));
			
			classFileRepository.saveAndFlush(tempFile);
		}
		else
		{
			tempFile = classFileRepository.findById(Integer.parseInt(fileInfo.getSeq())).get();
			tempFile.setClassSeq(Integer.parseInt(fileInfo.getClassSeq()));
			tempFile.setTitle(fileInfo.getTitle());
			tempFile.setContent(fileInfo.getEditor());
			tempFile.setWriter(member);
			tempFile.setFiles(fileInfo.getFiles(tempFile.getFiles()));
			classFileRepository.saveAndFlush(tempFile);
		}
		
		return tempFile;				
	}
}
