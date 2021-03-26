package com.igpgroup17.studentpals.controllers;

import com.igpgroup17.studentpals.models.adapters.event.EventAdapter;
import com.igpgroup17.studentpals.models.requestbody.RequestBodyEvent;
import com.igpgroup17.studentpals.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/v1/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(eventService.readEvent(id));
    }

    @PostMapping("/v1/events")
    public ResponseEntity<?> createEvent(@RequestBody RequestBodyEvent event) {
        return ResponseEntity.ok(eventService.createEvent(EventAdapter.fromRequestBodyEvent(event)));
    }

    @PutMapping("/v1/events")
    public ResponseEntity<?> updateEvent(@RequestBody RequestBodyEvent event) {
        return ResponseEntity.ok(eventService.updateEvent(EventAdapter.fromRequestBodyEvent(event)));
    }

    @DeleteMapping("/v1/events/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }

    @GetMapping("/v1/events/all/{id}")
    public ResponseEntity<?> getAllEventsFor(@PathVariable(name = "id") String organiserId) {
        return ResponseEntity.ok(eventService.getAllEventsFor(organiserId));
    }
}
