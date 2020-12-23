package com.tathink.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OfflineStudyInfo;

@Service
public class OfflineStudyInfoRepositoryService {
	@Autowired
	private OfflineStudyInfoRepository offlineStudyInfoRepository;
	
	public List<OfflineStudyInfo> getAllByCourseSeqBDelete(int courseSeq, boolean bDelete, Sort sort)
	{
		return offlineStudyInfoRepository.findByCourseSeqBDelete(courseSeq, bDelete, sort);
	}
	
	public Optional<OfflineStudyInfo> findById(int seq)
	{
		return offlineStudyInfoRepository.findById(seq);
	}
	
	public OfflineStudyInfo saveOfflineStudyInfo (OfflineStudyInfo info)
	{
		return offlineStudyInfoRepository.saveAndFlush(info);
	}
}
