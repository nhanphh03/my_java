package com.security.jwt.model;

import com.security.jwt.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Package: com.security.jwt.model
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDto userDto;

}