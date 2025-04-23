package org.example.jwtsecurity.controller;

import org.apache.coyote.BadRequestException;
import org.example.jwtsecurity.model.dto.UserRequestDTO;
import org.example.jwtsecurity.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDTO dto) throws BadRequestException {
        accountService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Void> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}