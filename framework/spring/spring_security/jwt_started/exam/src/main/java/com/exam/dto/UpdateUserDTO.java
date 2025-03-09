package com.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateUserDTO {
    private String fullName;
    private LocalDate dob;
}

