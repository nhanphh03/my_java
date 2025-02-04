package com.nhanph.security_started_1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.nhanph.security_started_1.dto
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
