package com.vayusense.apigetway.service;

import com.vayusense.apigetway.dto.AuthResponse;
import com.vayusense.apigetway.dto.UserDto;
import com.vayusense.apigetway.entities.Address;
import com.vayusense.apigetway.entities.Company;
import com.vayusense.apigetway.entities.Role;
import com.vayusense.apigetway.entities.User;
import com.vayusense.apigetway.errorhandler.BusinessException;
import com.vayusense.apigetway.errorhandler.ResourceNotFoundException;
import com.vayusense.apigetway.repository.UserRepository;
import com.vayusense.apigetway.util.JWTUtil;
import com.vayusense.apigetway.util.PBKDF2Encoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
/*import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
*/
//Exclude the code below to the authentication service
@Service
//@Slf4j
@AllArgsConstructor
public class UserDetailsService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
/*
   @PostConstruct
    public void init(){
        List<String> products = new ArrayList<String>();
        List<String> productsU = new ArrayList<String>();
        Company company = new Company();
        Company companyU = new Company();
        company.setName("vayusense");
        companyU.setName("vayusense");
        products.add("tobra");
        products.add("agent");
        products.add("vayumeter");
        productsU.add("tobra");
        company.setProduct(products);
        companyU.setProduct(productsU);
        company.setAddress(new Address("48 Ben Zion Galis St. Petach Tikva 4927948-Israel","+972-73-324-2761","Israel","Petach Tikva"));
        companyU.setAddress(new Address("48 Ben Zion Galis St. Petach Tikva 4927948-Israel","+972-73-324-2761" ,"Israel","Petach Tikva"));
        User userA = new User("admin", passwordEncoder.encode("admin"),"Daniel","Shmuel legend","daniel@vayusense.com","Y",company, true, Arrays.asList(Role.ROLE_ADMIN));
        User user = new User("algo", passwordEncoder.encode("algo"),"kobbi","bryant","algo@teva.com","N",companyU, true, Arrays.asList(Role.ROLE_USER));
        //User userA = new User("eppen", passwordEncoder.encode("eppen"),"eppen@eppen.com","N",companyU, true, Arrays.asList(Role.ROLE_EPPEN_USER));
        userRepository.saveAll(Flux.just(user,userA)).doOnError(e -> {
            log.error("find an error while user creating ", e);
    }).log().subscribe();

    }*/

    public Mono<ServerResponse> getToken(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.flatMap(user -> userRepository.findByUsername(user.getUsername())
                    .flatMap(userDetails -> {
                        if(userDetails.getPassword().equals(passwordEncoder.encode(user.getPassword()))){
                            return ServerResponse.ok().bodyValue(new AuthResponse(jwtUtil.generateToken(userDetails),userDetails.getCompany().getName(),userDetails.getCompany().getProduct()));
                        }else {
                            return ServerResponse.badRequest().build();
                        }
                    }).switchIfEmpty(ServerResponse.notFound().build())).log();
    }

    public Flux<UserDto> findByAdmin(String admin){
        return userRepository.findByAdmin(admin).map(UserDto::new).onErrorMap(e -> new BusinessException(e.getMessage()))
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Not found User by admin" +admin))).log();

    }

}
