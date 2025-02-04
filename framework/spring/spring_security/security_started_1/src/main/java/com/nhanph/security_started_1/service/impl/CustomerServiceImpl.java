package com.nhanph.security_started_1.service.impl;

import com.nhanph.security_started_1.dto.CustomerDTO;
import com.nhanph.security_started_1.entity.Customer;
import com.nhanph.security_started_1.repository.CustomerRepository;
import com.nhanph.security_started_1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Package: com.nhanph.security_started_1.service
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CustomerDTO customer) {
        try {
            return customerRepository.save(mapperCustomer(customer)).getId();
        } catch (Exception e) {
            throw new RuntimeException("Error while creating customer", e);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long idCustomer) {
        return convertToDTO(Objects.requireNonNull(customerRepository.findById(idCustomer).orElse(null)));
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    private Customer mapperCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }


}
