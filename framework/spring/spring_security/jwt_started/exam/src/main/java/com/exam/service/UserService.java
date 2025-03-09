package com.exam.service;


import com.exam.dto.CreateUserDTO;
import com.exam.dto.UpdateUserDTO;
import com.exam.entity.User;

public interface UserService {

    User createUser(CreateUserDTO dto);

    User updateUser(String username, UpdateUserDTO dto);

    void deleteUser(String username);

    int deleteUnder18();

}
