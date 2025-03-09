package com.exam.service.impl;

import com.exam.dto.CreateUserDTO;
import com.exam.dto.UpdateUserDTO;
import com.exam.entity.User;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tạo tài khoản người dùng
    @Autowired
    public User createUser(CreateUserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên người dùng đã tồn tại");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setDob(dto.getDob());
        user.setCreatedAt(LocalDate.now());

        return userRepository.save(user);
    }

    @Autowired
    public User updateUser(String username, UpdateUserDTO dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

        if (dto.getFullName() != null) user.setFullName(dto.getFullName());
        if (dto.getDob() != null) user.setDob(dto.getDob());

        return userRepository.save(user);
    }

    @Autowired
    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại");
        }
        userRepository.deleteByUsername(username);
    }

    @Autowired
    public int deleteUnder18() {
        LocalDate now = LocalDate.now();
        List<User> under18Users = userRepository.findByDobAfter(now.minusYears(18));

        int deletedCount = under18Users.size();
        userRepository.deleteAll(under18Users);

        return deletedCount;
    }
}
