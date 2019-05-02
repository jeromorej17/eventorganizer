package com.homedepot.eventorganizer.controller;

import com.homedepot.eventorganizer.model.Event;
import com.homedepot.eventorganizer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping(value = "/events/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") Long eventId) {
        Event event = eventService.getEventById(eventId);
        if(null != event){
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
