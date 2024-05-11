package com.github.authorization.controller;

import com.github.authorization.controller.request.AuthenticationReq;
import com.github.authorization.controller.request.RegisterReq;
import com.github.authorization.controller.response.AuthenticationRes;
import com.github.authorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRes> register (
            @RequestBody RegisterReq req) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRes> authenticate(
            @RequestBody AuthenticationReq req) {
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }
}
