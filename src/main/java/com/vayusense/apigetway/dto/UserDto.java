package com.vayusense.apigetway.dto;

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
    private String email;
    private String admin;
    private boolean enabled;
    private List<Role> roles;
    private Company company;


    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.admin = user.getAdmin();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.company = user.getCompany();

    }
}
