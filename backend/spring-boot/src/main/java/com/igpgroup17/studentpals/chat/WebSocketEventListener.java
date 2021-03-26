package com.igpgroup17.studentpals.chat;

import com.igpgroup17.studentpals.models.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessageBrokerConfigurer.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void onJoin(SessionConnectedEvent event) {
        LOGGER.info("New client connected");
    }

    @EventListener
    public void onLeave(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String user = (String) accessor.getSessionAttributes().get("username");
        ChatMessage message = ChatMessage.builder()
                .type(ChatMessage.Type.LEAVE)
                .sender(user)
                .build();

        sendingOperations.convertAndSend("/topic/public", message);
    }
}
