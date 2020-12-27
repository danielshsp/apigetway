package com.vayusense.apigetway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    @GetMapping("/api/v1/message")
    public Mono<String> getMessage(){
        return Mono.just("xxx");
    }
}
