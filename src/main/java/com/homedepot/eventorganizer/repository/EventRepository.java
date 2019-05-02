package com.homedepot.eventorganizer.repository;

import com.homedepot.eventorganizer.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {
}
