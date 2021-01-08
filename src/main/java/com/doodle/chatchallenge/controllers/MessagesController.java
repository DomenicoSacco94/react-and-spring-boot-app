package com.doodle.chatchallenge.controllers;

import com.doodle.chatchallenge.models.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {

    @GetMapping
    public List<Message> list() {
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Message message) {

    }
}
