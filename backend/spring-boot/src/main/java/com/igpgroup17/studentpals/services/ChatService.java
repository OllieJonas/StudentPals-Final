package com.igpgroup17.studentpals.services;


public interface ChatService {

    String joinRoom(String roomId, String userId);

    String leaveRoom(String roomId, String userId);

    String helloWorld();
}
