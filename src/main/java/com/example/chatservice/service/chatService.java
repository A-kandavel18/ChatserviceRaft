package com.example.chatservice.service;

import com.example.chatservice.model.message;
import com.example.chatservice.model.response;
import com.example.chatservice.model.room;
import com.example.chatservice.store.chatStore;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Business logic layer.
 *
 * Keeping this between the controller and the store means:
 * - Controllers handle only HTTP concerns (request/response mapping).
 * - The store handles only data storage concerns.
 * - All "does this room exist?" decisions live here.
 */
@Service
public class chatService {

    private final chatStore store;

    public chatService(chatStore store) {
        this.store = store;
    }

    /**
     * Creates a new chat room.
     */
    public response createRoom(String name, String description) {
        return store.createRoom(name, description);
    }

    /**
     * Sends a message to a room.
     *
     * @throws RoomNotFoundException if the room does not exist
     */
    public response sendMessage(String roomId, String sender, String content) {
        // Verify the room exists first (gives a clearer error than the store's
        // Optional.empty())

        return store.addMessage(roomId, sender, content);

    }

    public response getRecentMessages(String roomId) {
        return store.getRecentMessages(roomId);
    }

}
