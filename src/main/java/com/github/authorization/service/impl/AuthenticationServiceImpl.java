package com.github.authorization.service.impl;

import com.github.authorization.controller.request.AuthenticationReq;
import com.github.authorization.controller.request.RegisterReq;
import com.github.authorization.controller.response.AuthenticationRes;
import com.github.authorization.domain.entity.UserAccountEntity;
import com.github.authorization.dto.CreateUserAccountDto;
import com.github.authorization.service.AuthenticationService;
import com.github.authorization.service.JwtService;
import com.github.authorization.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationRes register(RegisterReq registerReq) {
        log.info("Registering user with email: {}", registerReq.getEmail());
        CreateUserAccountDto createUserAccountDto = new CreateUserAccountDto();
        createUserAccountDto.setFirstname(registerReq.getFirstname());
        createUserAccountDto.setLastname(registerReq.getLastname());
        createUserAccountDto.setEmail(registerReq.getEmail());
        createUserAccountDto.setPassword(registerReq.getPassword());

        UserAccountEntity userAccountEntity =
                userAccountService.createUserAccount(createUserAccountDto);

        String jwtToken = jwtService.generateToken(userAccountEntity);
        return AuthenticationRes.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationRes authenticate(AuthenticationReq authenticationReq) {
        log.info("Authenticating user with email: {}", authenticationReq.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationReq.getEmail(),
                        authenticationReq.getPassword()
                )
        );

        UserAccountEntity userAccountEntity =
                userAccountService.getUserByEmail(authenticationReq.getEmail());
        String jwtToken = jwtService.generateToken(userAccountEntity);

        return AuthenticationRes.builder()
                .token(jwtToken)
                .build();
    }
}
