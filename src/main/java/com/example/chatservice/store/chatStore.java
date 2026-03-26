package com.example.chatservice.store;

import com.example.chatservice.model.exception;
import com.example.chatservice.model.message;
import com.example.chatservice.model.response;
import com.example.chatservice.model.room;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Thread-safe in-memory store for rooms and messages.
 *
 * Design decisions:
 * -
 */
@Component
public class chatStore {

    private final int maxMessages;
    private final AtomicInteger roomIdCounter = new AtomicInteger(0);
    private final AtomicInteger messageIdCounter = new AtomicInteger(0);
    private final ArrayList<room> rooms = new ArrayList<room>();
    private final ArrayList<message> roomMessages = new ArrayList<message>();

    public chatStore(@Value("${chat.service.max-messages:20}") int maxMessages) {
        this.maxMessages = maxMessages;
    }

    // -------------------------------------------------------------------------
    // Rooms
    // -------------------------------------------------------------------------

    /**
     * Creates a new room and returns it.
     */
    public response createRoom(String name, String description) {
        String id = "room-" + roomIdCounter.incrementAndGet();
        response res = new response();

        room room = new room(id, name, description);

        rooms.add(room);
        res.chatRoom = room;
        return res;
    }

    // -------------------------------------------------------------------------
    // Messages
    // -------------------------------------------------------------------------

    /**
     * Appends a message to the specified room.
     *
     * @return the persisted message, or empty if the room does not exist
     */
    public response addMessage(String roomId, String sender, String content) {
        response res = new response();
        if (roomMessages.size() < maxMessages - 1) {
            if (findRoomById(roomId) != null) {
                String msgId = "message-" + messageIdCounter.incrementAndGet();

                message roomMsg = new message(msgId, roomId, sender, content);
                roomMessages.add(roomMsg);
                res.chatMessage = roomMsg;
            } else {
                exception ex = new exception("room not found",
                        "404");
                res.methException = ex;

            }

        } else {
            exception ex = new exception("exceeded maximum message count",
                    "402");
            res.methException = ex;

        }
        return res;

    }

    public response getRecentMessages(String roomId) {
        response res = new response();

        if (roomMessages != null && roomMessages.size() > 0) {
            List<message> roommsgs = roomMessages.stream()
                    .filter(roommsg -> roomId.equals(roommsg.roomId()))
                    .collect(Collectors.toList());
            res.roomMessages = roommsgs;

        }

        else {
            exception ex = new exception("There are no messages in the room",
                    "404");

            res.methException = ex;
        }
        return res;

    }

    // ... in your main class
    private room findRoomById(String roomId) {
        for (room item : rooms) {
            if (item.id().equals(roomId)) {
                return item; // return the object when found
            }
        }
        return null; // return null if no match is found
    }

}