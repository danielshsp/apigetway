package com.vayusense.apigetway.service;

import com.vayusense.apigetway.dto.AuthResponse;
import com.vayusense.apigetway.entities.User;
import com.vayusense.apigetway.repository.UserRepository;
import com.vayusense.apigetway.util.JWTUtil;
import com.vayusense.apigetway.util.PBKDF2Encoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
/*import reactor.core.publisher.Flux;
import javax.annotation.PostConstruct;
import java.util.Arrays;*/


@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
/*
   @PostConstruct
    public void init(){
        //username:passwowrd -> user:user
        User user = new User("user", passwordEncoder.encode("user"),"daniel1@vayusense.com","vayusense", true, Arrays.asList(Role.ROLE_USER));
        User userA = new User("admin", passwordEncoder.encode("admin"),"daniel@vayusense.com","vayusense", true, Arrays.asList(Role.ROLE_ADMIN));
        userRepository.saveAll(Flux.just(user,userA)).doOnError(e -> {
            log.error("find an error while user creating ", e);
        }).log().subscribe();

    }*/

    public Mono<ServerResponse> getToken(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.flatMap(user -> userRepository.findByUsername(user.getUsername())
                    .flatMap(userDetails -> {
                        if(userDetails.getPassword().equals(passwordEncoder.encode(user.getPassword()))){
                            return ServerResponse.ok().bodyValue(new AuthResponse(jwtUtil.generateToken(userDetails)));
                        }else {
                            return ServerResponse.badRequest().build();
                        }
                    }).switchIfEmpty(ServerResponse.badRequest().build()));
    }
}
