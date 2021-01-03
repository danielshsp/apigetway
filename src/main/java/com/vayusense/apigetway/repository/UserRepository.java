package com.vayusense.apigetway.repository;

import com.vayusense.apigetway.dto.UserDto;
import com.vayusense.apigetway.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
    Flux<User> findByAdmin(String admin);
}
