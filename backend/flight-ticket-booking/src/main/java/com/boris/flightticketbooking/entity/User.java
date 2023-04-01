package com.boris.flightticketbooking.entity;

import com.boris.flightticketbooking.entity.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;

    @NonNull
    private String email;
    @JsonIgnore
    @NonNull
    private String password;
    private String firstName;
    private String lastName;

    private Role role;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(UserDTO userDTO){
        this.email =  userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.firstName= userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role.name());
        return Collections.singletonList(simpleGrantedAuthority);

//        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
