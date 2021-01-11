package com.doodle.chatchallenge.services;

import com.doodle.chatchallenge.models.Message;
import com.doodle.chatchallenge.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository= messageRepository;
    }

    private MessageRepository messageRepository;

    public List<Message> list() {
        List<Message> messages = messageRepository.findAll();
        messages.sort(Comparator.comparing(Message::getSendDate));
        return messages;
    }

    public Message create(Message message) {
        messageRepository.save(message);
        return message;
    }
}
