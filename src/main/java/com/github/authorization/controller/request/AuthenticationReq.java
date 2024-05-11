package com.github.authorization.controller.request;

import lombok.Data;

@Data
public class AuthenticationReq {
    private String email;
    private String password;
}
