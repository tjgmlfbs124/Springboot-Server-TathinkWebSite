package com.tathink.database.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tathink.database.model.CourseLevel;

@Service
public class CourseLevelRepositoryService {
	@Autowired
	private CourseLevelRepository courseLevelRepository;
	
	public List<CourseLevel> getAll()
	{
		return courseLevelRepository.findAll(new Sort(Direction.ASC, "seq"));
	}
	
	public String findLevelName(int seq)
	{
		return courseLevelRepository.findLevelNameByLevelSeq(seq);
	}
}
