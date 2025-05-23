package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User registerNewUser(String username, String password, String email, String firstName, String lastName);
    Optional<User> getUserById(Long id);
}