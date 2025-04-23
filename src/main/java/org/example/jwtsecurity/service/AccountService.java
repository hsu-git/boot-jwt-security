package org.example.jwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.jwtsecurity.model.dto.UserRequestDTO;
import org.example.jwtsecurity.model.entity.Account;
import org.example.jwtsecurity.model.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequestDTO dto) throws BadRequestException {
        if (dto.username().isEmpty() || dto.password().isEmpty()) {
            throw new BadRequestException("잘못된 입력!");
        }
        Account account = new Account();
        account.setUsername(dto.username());
        account.setPassword(passwordEncoder.encode(dto.password())); // BCrypt
        // UUID.
        accountRepository.save(account);
    }
}