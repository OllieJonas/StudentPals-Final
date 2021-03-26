package com.igpgroup17.studentpals.models.adapters.event;

import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.EventPreview;

public class EventPreviewAdapter {

    public static EventPreview adapt(Event event) {
        return EventPreview.builder().eventId(event.getEventID()).description(event.getDescription()).name(event.getName()).dateTime(event.getScheduledTime()).likes(event.getLikes()).build();
    }
}
