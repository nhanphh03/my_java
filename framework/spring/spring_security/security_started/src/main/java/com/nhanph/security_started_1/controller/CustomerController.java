package com.nhanph.security_started_1.controller;

import com.nhanph.security_started_1.dto.CustomerDTO;
import com.nhanph.security_started_1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CustomerDTO> getCustomerList(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CustomerDTO>> getCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
}
