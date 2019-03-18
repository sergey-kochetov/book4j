package com.melt.book4j.controller;

import com.sun.javafx.collections.MappingChange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MessageController {

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Third");}});
    }};
    @GetMapping("message")
    public List<Map<String, String>> list() {
        return messages;
    }
}
