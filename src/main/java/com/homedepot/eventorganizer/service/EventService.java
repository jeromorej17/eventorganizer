package com.homedepot.eventorganizer.service;

import com.homedepot.eventorganizer.model.Event;
import com.homedepot.eventorganizer.repository.EventRepository;
import com.homedepot.eventorganizer.util.Interval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    private static final Logger logger = LogManager.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(optionalEvent.isPresent()){
            Event event = optionalEvent.get();
            String duration = Interval.getInterval(event.getEventDuration());
            logger.error(duration);
            return event;
        }
        return null;
    }
}
