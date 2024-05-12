package com.github.authorization.service;

import com.github.authorization.domain.entity.UserAccountEntity;
import com.github.authorization.dto.CreateUserAccountDto;

public interface UserAccountService {
    UserAccountEntity createUserAccount(CreateUserAccountDto createUserAccountDto);
    UserAccountEntity getUserByEmail(String email);
}
