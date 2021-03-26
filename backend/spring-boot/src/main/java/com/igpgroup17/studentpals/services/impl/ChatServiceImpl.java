package com.igpgroup17.studentpals.services.impl;

import akka.actor.typed.ActorSystem;
import com.igpgroup17.studentpals.chat.ChatServerActor;
import com.igpgroup17.studentpals.services.ChatService;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Getter(lazy = true)
    private final ActorSystem<ChatServerActor.Command> chatServerActor = ActorSystem.apply(ChatServerActor.create(), "Chat Server");

    @Override
    public String joinRoom(String roomId, String userId) {
        return null;
    }

    @Override
    public String leaveRoom(String roomId, String userId) {
        return null;
    }

    @Override
    public String helloWorld() {
        return "Hello world!";
    }


}
