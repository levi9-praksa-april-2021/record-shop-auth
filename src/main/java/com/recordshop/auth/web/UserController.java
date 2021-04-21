package com.recordshop.auth.web;

import com.recordshop.auth.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @PostMapping("")
    ResponseEntity<Void> register(@RequestBody @Valid UserRegistrationRequest request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Void> handleEntityExists(
            EntityExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
