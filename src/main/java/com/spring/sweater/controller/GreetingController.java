package com.spring.sweater.controller;

import com.spring.sweater.model.Message;
import com.spring.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                                   String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());
        return "index";
    }

    @PostMapping
    public String createMessages(@RequestParam String text, @RequestParam String tag,
                                 Map<String, Object> model) {
        messageRepository.save(new Message(text, tag));
        model.put("messages", messageRepository.findAll());
        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepository.findAllByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.put("messages", messages);
        return "index";
    }

}