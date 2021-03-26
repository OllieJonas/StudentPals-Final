package com.igpgroup17.studentpals.chat;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServerActor extends AbstractBehavior<ChatServerActor.Command> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServerActor.class);

    public interface Command {}

    public enum CreateRoom implements Command {
        INSTANCE
    }

    public static class DeleteRoom implements Command {
        public final String roomId;

        public DeleteRoom(String roomId) {
            this.roomId = roomId;
        }
    }

    private ChatServerActor(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(ChatServerActor::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(CreateRoom.INSTANCE, this::onCreateRoom)
                .onMessage(DeleteRoom.class, this::onDeleteRoom)
                .onAnyMessage(this::onError)
                .build();
    }

    private Behavior<Command> onCreateRoom() {
        return this;
    }

    private Behavior<Command> onDeleteRoom(DeleteRoom room) {
        return this;
    }

    private Behavior<Command> onError(Command c) {
        LOGGER.error("Unknown command " + c.toString() + " in ChatServerActor");
        return this;
    }


}
