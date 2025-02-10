package com.security.jwt.mapper;

import com.security.jwt.dto.UserDto;
import com.security.jwt.entity.User;

/**
 * @Package: com.security.jwt.mapper
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */
public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
