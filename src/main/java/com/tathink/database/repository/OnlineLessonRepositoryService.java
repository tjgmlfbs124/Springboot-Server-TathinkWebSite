package com.tathink.database.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tathink.database.model.OnlineLesson;

@Service
public class OnlineLessonRepositoryService {
	@Autowired
	private OnlineLessonRepository onlineLessonRepository;
	
	public List<OnlineLesson> getAllByOnlineStudyInfoSeq(int studyInfoSeq, Sort sort)
	{
		return  onlineLessonRepository.findAllByOnlineStudyInfoSeq(studyInfoSeq, sort);
	}
}
