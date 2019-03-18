package com.melt.book4j.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.melt.book4j.domain.Message;
import com.melt.book4j.domain.Views;
import com.melt.book4j.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("message")
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("message/{id}")
    public Message getById(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping("message")
    public Message add(@RequestBody Message message) {
        message.setCreateDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PutMapping("message/{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("message/{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }
}
