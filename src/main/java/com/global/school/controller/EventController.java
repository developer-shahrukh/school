package com.global.school.controller;


import com.global.school.entity.Event;
import com.global.school.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Validated @RequestBody Event event){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(event));
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> getByEventId(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getByEventId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Validated @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long  id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
