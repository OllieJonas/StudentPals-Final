package com.igpgroup17.studentpals.services;
import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.Student;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event readEvent(String id);

    Event updateEvent(Event event);

    Event deleteEvent(String id);

    List<Event> getAllEventsFor(String organiserId);
}
