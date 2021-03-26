package com.igpgroup17.studentpals.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ChatMessage {


    private final String message;

    private final Type type;

    private final String sender;

    private final String dateTime;

    public enum Type {
        JOIN,
        LEAVE,
        MESSAGE
    }
}
