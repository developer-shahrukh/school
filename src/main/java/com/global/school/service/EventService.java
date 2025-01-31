package com.global.school.service;

import com.global.school.entity.Event;
import com.global.school.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }
    public Event getByEventId(Long eventId){
        return eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("Event not found."));
    }
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public void deleteEvent(Long eventId){
        eventRepository.deleteById(eventId);
    }
    public Event updateEvent(Long eventId, Event event){
        Event existingEvent=getByEventId(eventId);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setAClass(event.getAClass());
        return eventRepository.save(existingEvent);
    }
}
