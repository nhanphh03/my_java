package com.nhanph.security_started_1.repository;

import com.nhanph.security_started_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Package: com.nhanph.security_started_1.repository
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */

@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
