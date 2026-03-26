package com.example.chatservice.model;

/**
 * Request body for POST /rooms/{roomId}/messages.
 */
public record sendMessageRequest(

                String sender,

                String content) {
}