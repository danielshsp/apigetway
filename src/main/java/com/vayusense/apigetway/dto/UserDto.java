package com.vayusense.apigetway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vayusense.apigetway.entities.Company;
import com.vayusense.apigetway.entities.Role;
import com.vayusense.apigetway.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    private String email;
    private String admin;
    private boolean enabled;
    private List<Role> roles;
    private Company company;

    //map to entity domain
    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.admin = user.getAdmin();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.company = user.getCompany();

    }
}
