package com.ali.store.controllers;

import com.ali.store.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
        @RequestMapping("/hello")
        public Message SayHello() {

            return new Message("Hello World!");
        }
    }
