package com.ecograd.ecograd.service;

import com.ecograd.ecograd.model.Role;
import com.ecograd.ecograd.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User register(String username, String password, String name, String surname, String email, LocalDate dateOfBirth, Integer points, Role role);

    User findByUsername(String username);
    User addPointsToUser(Double points, String username);
}
