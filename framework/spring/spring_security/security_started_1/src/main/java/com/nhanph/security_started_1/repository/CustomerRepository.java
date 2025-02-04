package com.nhanph.security_started_1.repository;

import com.nhanph.security_started_1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.nhanph.security_started_1.repository
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
