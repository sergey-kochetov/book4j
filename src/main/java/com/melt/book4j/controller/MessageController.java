package com.melt.book4j.controller;

import com.melt.book4j.exception.NotFoundException;
import com.sun.javafx.collections.MappingChange;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MessageController {

    private int counter = 4;

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second");}});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third");}});
    }};

    @GetMapping("message")
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("message/{id}")
    public Map<String, String> getById(@PathVariable String id) {
        return getMessageById(id);
    }

    @PostMapping("message")
    public Map<String, String> add(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @PutMapping("message/{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessageById(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("message/{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessageById(id);
        messages.remove(message);
    }

    private Map<String, String> getMessageById(@PathVariable String id) {
        return messages.stream()
                .filter(msg -> msg.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
