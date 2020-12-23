package com.tathink.database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tathink.database.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("select event from Event event")
	List<Event> findAllBySeqOrder(Sort sort);
}
