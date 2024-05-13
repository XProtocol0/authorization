package com.github.authorization.service.impl;

import com.github.authorization.constants.Role;
import com.github.authorization.domain.entity.UserAccountEntity;
import com.github.authorization.dto.CreateUserAccountDto;
import com.github.authorization.repository.UserAccountRepository;
import com.github.authorization.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserAccountEntity createUserAccount(CreateUserAccountDto createUserAccountDto) {
        log.info("Creating user with email: {}", createUserAccountDto.getEmail());
        checkIfUserAlreadyExists(createUserAccountDto);

        return userAccountRepository.save(
                UserAccountEntity.builder()
                        .firstname(createUserAccountDto.getFirstname())
                        .lastname(createUserAccountDto.getLastname())
                        .email(createUserAccountDto.getEmail())
                        .password(passwordEncoder.encode(createUserAccountDto.getPassword()))
                        .role(Role.USER)
                        .build()
        );
    }

    @Override
    public UserAccountEntity getUserByEmail(String email) {

        log.info("Getting user account for email: {}", email);
        Optional<UserAccountEntity> entity =
                userAccountRepository.findByEmail(email);
        if(entity.isEmpty()) {
            log.error("User does not exists with email: {}", email);
            return null;
        }
        return entity.get();
    }

    private void checkIfUserAlreadyExists(CreateUserAccountDto createUserAccountDto) {
        log.info("Checking if user with email: {} is already registered", createUserAccountDto.getEmail());
        Optional<UserAccountEntity> entity =
                userAccountRepository.findByEmail(createUserAccountDto.getEmail());
        if(entity.isPresent()) {
            log.error("User already exists for email : {}", createUserAccountDto.getEmail());
        }
    }

}
