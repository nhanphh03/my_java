package com.exam.controller;

import com.exam.dto.CreateUserDTO;
import com.exam.dto.UpdateUserDTO;
import com.exam.entity.User;
import com.exam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tạo tài khoản người dùng
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(username, dto));
    }

    // Xóa người dùng theo username
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // Xóa người dùng dưới 18 tuổi
    @DeleteMapping("/under18")
    public ResponseEntity<String> deleteUnder18() {
        int deletedCount = userService.deleteUnder18();
        return ResponseEntity.ok("Đã xóa " + deletedCount + " người dùng dưới 18 tuổi");
    }
}

