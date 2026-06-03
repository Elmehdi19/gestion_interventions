package com.pgi.pgic.controller;

import com.pgi.pgic.dto.ChatbotMessageRequest;
import com.pgi.pgic.dto.ChatbotMessageResponse;
import com.pgi.pgic.service.ChatbotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired private ChatbotService chatbotService;

    @PostMapping("/message")
    public ResponseEntity<ChatbotMessageResponse> sendMessage(@Valid @RequestBody ChatbotMessageRequest request) {
        return ResponseEntity.ok(chatbotService.processMessage(request));
    }
}