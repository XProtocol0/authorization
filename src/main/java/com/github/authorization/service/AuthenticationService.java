package com.github.authorization.service;

import com.github.authorization.controller.request.AuthenticationReq;
import com.github.authorization.controller.request.RegisterReq;
import com.github.authorization.controller.response.AuthenticationRes;

public interface AuthenticationService {
    AuthenticationRes register(RegisterReq registerReq);
    AuthenticationRes authenticate(AuthenticationReq authenticationReq);
}
