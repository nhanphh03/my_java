package com.security.jwt.service.impl;

import com.security.jwt.constant.Role;
import com.security.jwt.dto.UserDto;
import com.security.jwt.entity.Token;
import com.security.jwt.entity.User;
import com.security.jwt.mapper.UserMapper;
import com.security.jwt.model.AuthenticationRequest;
import com.security.jwt.model.AuthenticationResponse;
import com.security.jwt.model.RegisterRequest;
import com.security.jwt.repository.TokenRepository;
import com.security.jwt.repository.UserRepository;
import com.security.jwt.service.AuthenticationService;
import com.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Package: com.security.jwt.service.impl
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(Role.ADMIN);
        User createdUser = userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(createdUser);

        Token token = Token.builder()
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .userId(createdUser.getUserId())
                .build();
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userDto(UserMapper.mapToUserDto(createdUser))
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        UserDto userDto = UserMapper.mapToUserDto(user);
        return AuthenticationResponse.builder()
                .userDto(userDto)
                .token(jwtToken)
                .build();
    }
}
