package com.github.authorization.controller;

import com.github.authorization.domain.entity.UserAccountEntity;
import com.github.authorization.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("{email}")
    public ResponseEntity<UserAccountEntity> getUserAccountByEmail(
            @PathVariable String email
    ) {
        log.info("Getting user account for email: {}", email);
        UserAccountEntity userAccountEntity = userAccountService.getUserByEmail(email);
        return ResponseEntity.ok(userAccountEntity);
    }
}
