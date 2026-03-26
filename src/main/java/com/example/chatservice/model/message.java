package com.example.chatservice.model;

/**
 * Represents a chat message within a room.
 */
public record message(
        String id,
        String roomId,
        String sender,
        String content

) {
}