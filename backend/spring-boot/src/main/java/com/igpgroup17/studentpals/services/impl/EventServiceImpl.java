package com.igpgroup17.studentpals.services.impl;

import com.igpgroup17.studentpals.dao.EventCrudDao;
import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.Student;
import com.igpgroup17.studentpals.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventCrudDao eventCrudDao;

    @Autowired
    public EventServiceImpl(EventCrudDao eventCrudDao) {
        this.eventCrudDao = eventCrudDao;
    }

    @Override
    public Event createEvent(Event event) {
        return eventCrudDao.createEvent(event);
    }

    @Override
    public Event readEvent(String id) {
        return eventCrudDao.readEvent(id);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventCrudDao.updateEvent(event);
    }

    @Override
    public Event deleteEvent(String id) {
        Event event = eventCrudDao.readEvent(id);
        eventCrudDao.deleteEvent(id);
        return event;
    }

    @Override
    public List<Event> getAllEventsFor(String organiserId) {
        return eventCrudDao.getAllEventsFor(organiserId);
    }
}
