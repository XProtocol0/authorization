package com.github.authorization.controller.request;

import lombok.Data;

@Data
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
