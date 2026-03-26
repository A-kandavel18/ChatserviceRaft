package com.example.chatservice.model;

/**
 * Request body for POST /rooms.
 */
public record createRoomRequest(

        String name,

        String description) {
}