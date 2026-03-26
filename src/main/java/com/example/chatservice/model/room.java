package com.example.chatservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a chat room.
 *
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record room(
        String id,
        String name,
        String description

) {
}