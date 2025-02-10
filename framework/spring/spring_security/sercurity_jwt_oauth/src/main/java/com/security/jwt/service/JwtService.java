package com.security.jwt.service;

import com.security.jwt.entity.User;

/**
 * @Package: com.security.jwt.service
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */
public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);

}
