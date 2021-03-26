package com.igpgroup17.studentpals.chat;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ChatRoomActor extends AbstractBehavior<ChatRoomActor.MessageType> {

    interface MessageType {
        String sender();

        String room();
    }

    public static class Join implements MessageType {

        private final String sender;

        private final String room;

        public Join(String sender, String room) {
            this.sender = sender;
            this.room = room;
        }

        public String sender() {
            return sender;
        }

        public String room() {
            return room;
        }
    }

    public static class Leave implements MessageType {

        private final String sender;

        private final String room;

        public Leave(String sender, String room) {
            this.sender = sender;
            this.room = room;
        }

        public String sender() {
            return sender;
        }

        public String room() {
            return room;
        }
    }

    public static class Message implements MessageType {

        private final String sender;

        private final String room;

        public final String message;

        public Message(String sender, String room, String message) {
            this.sender = sender;
            this.room = room;
            this.message = message;
        }

        public String sender() {
            return sender;
        }

        public String room() {
            return room;
        }
    }

    private ChatRoomActor(ActorContext<MessageType> context) {
        super(context);
    }

    public static Behavior<MessageType> create() {
        return Behaviors.setup(ChatRoomActor::new);
    }

    @Override
    public Receive<MessageType> createReceive() {
        return newReceiveBuilder()
                .onMessage(Join.class, this::onJoin)
                .onMessage(Leave.class, this::onLeave)
                .onMessage(Message.class, this::onMessage)
                .build();
    }

    private Behavior<MessageType> onJoin(Join join) {
        return this;
    }

    private Behavior<MessageType> onLeave(Leave leave) {
        return this;
    }

    private Behavior<MessageType> onMessage(Message message) {
        return this;
    }
}
