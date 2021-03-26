package com.igpgroup17.studentpals.models;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class EventPreview {

    private final String eventId;

    private final String name;

    private final String description;

    private final String dateTime;

    private final int likes;
}
