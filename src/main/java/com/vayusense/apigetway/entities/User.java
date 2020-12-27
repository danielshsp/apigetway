package com.vayusense.apigetway.entities;

import com.vayusense.apigetway.service.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String admin;
    private boolean enabled;
    private List<Role> roles;
    private Company company;

    public User(String username, String password, String email, String admin, Company company, boolean enabled, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.company = company;
        this.enabled = enabled;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
