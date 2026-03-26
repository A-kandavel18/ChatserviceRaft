package com.example.chatservice.controller;

import com.example.chatservice.model.*;

import com.example.chatservice.service.chatService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller — responsible only for HTTP mapping.
 * All business logic is delegated to {@link ChatService}.
 */
@RestController
@RequestMapping("/rooms")
public class chatServiceController {

    private final chatService svc;

    public chatServiceController(chatService chatSvc) {
        this.svc = chatSvc;
    }

    /**
     * POST /rooms
     * Creates a new chat room.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public response createRoom(@RequestBody createRoomRequest request) {
        return svc.createRoom(request.name(), request.description());
    }

    /**
     * POST /rooms/{roomId}/messages
     * Sends a message to a room.
     */
    @PostMapping("/{roomId}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public response sendMessage(
            @PathVariable String roomId,
            @RequestBody sendMessageRequest request) {

        return svc.sendMessage(roomId, request.sender(), request.content());
    }

    /**
     * GET /rooms/{roomId}/messages
     * Returns the most recent messages from a room.
     */
    @GetMapping("/{roomId}/messages")
    public response getMessages(@PathVariable String roomId) {
        return svc.getRecentMessages(roomId);
    }
}