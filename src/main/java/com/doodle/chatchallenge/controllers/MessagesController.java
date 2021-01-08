package com.doodle.chatchallenge.controllers;

import com.doodle.chatchallenge.models.Message;
import com.doodle.chatchallenge.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Message> list() {
        List<Message> messages = messageRepository.findAll();
        messages.sort(Comparator.comparing(Message::getSendDate));
        return messages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Message message) {
        messageRepository.save(message);
    }
}
