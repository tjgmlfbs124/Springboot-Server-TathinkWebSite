package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OnlineStudyInfo;

@Service
public class OnlineStudyInfoRepositoryService {
	@Autowired
	private OnlineStudyInfoRepository onlineStudyInfoRepository;
	
	public List<OnlineStudyInfo> getAllByCourseSeqBDelete(int courseSeq, boolean bDelete, Sort sort)
	{
		return onlineStudyInfoRepository.findByCourseSeqBDelete(courseSeq, bDelete, sort);
	}
	
	public Optional<OnlineStudyInfo> findById(int studyInfoSeq)
	{
		return onlineStudyInfoRepository.findById(studyInfoSeq);
	}
}
