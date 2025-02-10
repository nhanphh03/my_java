package com.security.jwt.service;

import com.security.jwt.model.AuthenticationRequest;
import com.security.jwt.model.AuthenticationResponse;
import com.security.jwt.model.RegisterRequest;

/**
 * @Package: com.security.jwt.service
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);
}
