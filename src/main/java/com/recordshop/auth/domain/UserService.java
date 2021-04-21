package com.recordshop.auth.domain;

import com.recordshop.auth.web.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void register(UserRegistrationRequest request) {
        if (userExists(request.getEmail()))
            throw new EntityExistsException("User with email " + request.getEmail() + " already exists");

        User user = User.builder()
                .id(null)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(Collections.singletonList(roleRepository.findByName("ROLE_USER")))
                .build();

        userRepository.save(user);
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
