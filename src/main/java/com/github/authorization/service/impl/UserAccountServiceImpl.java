package com.github.authorization.service.impl;

import com.github.authorization.constants.Role;
import com.github.authorization.domain.entity.UserAccountEntity;
import com.github.authorization.dto.CreateUserAccountDto;
import com.github.authorization.repository.UserAccountRepository;
import com.github.authorization.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserAccountEntity createUserAccount(CreateUserAccountDto createUserAccountDto) {

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
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void checkIfUserAlreadyExists(CreateUserAccountDto createUserAccountDto) {
        Optional<UserAccountEntity> entity =
                userAccountRepository.findByEmail(createUserAccountDto.getEmail());
        if(entity.isEmpty()) {
            //throw error
        }
        return;
    }

}
