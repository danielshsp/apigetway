package com.vayusense.apigetway.web;

import com.vayusense.apigetway.service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserDetailsRouter {

    @Bean
    public RouterFunction<ServerResponse> root(UserDetailsService userDetailsService){
        return RouterFunctions.route()
                .POST("/auth/login", userDetailsService::getToken)
                .build();
    }
}
