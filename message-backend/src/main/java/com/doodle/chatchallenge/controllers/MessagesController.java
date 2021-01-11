package com.doodle.chatchallenge.controllers;

import com.doodle.chatchallenge.models.Message;
import com.doodle.chatchallenge.repositories.MessageRepository;
import com.doodle.chatchallenge.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Contains the controller methods managing the <code>Message</code> entity
 */
@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    private MessagesService messagesService;

    @GetMapping
    public List<Message> list() {
        return messagesService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@RequestBody Message message) {
      return messagesService.create(message);
    }
}
