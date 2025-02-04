package com.nhanph.security_started_1.service;

import com.nhanph.security_started_1.dto.CustomerDTO;

import java.util.List;

/**
 * @Package: com.nhanph.security_started_1.service
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */

public interface CustomerService {
    Long createCustomer(CustomerDTO customer);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO getCustomer(Long idCustomer);
}
