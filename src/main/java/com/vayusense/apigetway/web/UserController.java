package com.vayusense.apigetway.web;

import com.vayusense.apigetway.dto.UserDto;
import com.vayusense.apigetway.errorhandler.Admin;
import com.vayusense.apigetway.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserDetailsService userDetailsService;

    @GetMapping("")
    public Flux<UserDto> findByAdmin(@Admin @RequestParam String admin){
        return userDetailsService.findByAdmin(admin);

    }
}
