package com._anhn.controllers;

import com._anhn.models.Chat;
import com._anhn.models.Message;
import com._anhn.models.User;
import com._anhn.request.MessageRequest;
import com._anhn.services.MessageService;
import com._anhn.services.ProjectService;
import com._anhn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Repository
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) throws Exception {
        User user = userService.findByUserId(messageRequest.getSenderId());
        Chat chat = projectService.getChatByProjectId(messageRequest.getProjectId());
        if(chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found with id: " + messageRequest.getProjectId());
        }
        Message message = messageService.sendMessage(messageRequest.getSenderId(), messageRequest.getProjectId(), messageRequest.getContent());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<?> getChatByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(projectService.getChatByProjectId(projectId));
    }

}
