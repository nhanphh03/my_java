package com.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateUserDTO {

    @NotBlank(message = "Tên người dùng không được để trống")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Tên người dùng chỉ được nhập ký tự A-z")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    private String fullName;
    private LocalDate dob;
}

