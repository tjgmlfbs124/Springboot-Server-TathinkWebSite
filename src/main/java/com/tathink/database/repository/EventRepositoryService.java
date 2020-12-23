package com.tathink.database.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tathink.database.model.Event;

@Service
public class EventRepositoryService {
	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> getAll()
	{
		return eventRepository.findAllBySeqOrder(new Sort(Direction.ASC, "seq"));
	}
}
