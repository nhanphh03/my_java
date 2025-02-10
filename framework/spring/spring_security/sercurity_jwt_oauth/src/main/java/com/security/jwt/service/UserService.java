package com.security.jwt.service;

import com.security.jwt.entity.User;

import java.util.List;

/**
 * @Package: com.security.jwt.service
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */
public interface UserService {
    User createUser(User user);

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, User user);
}
