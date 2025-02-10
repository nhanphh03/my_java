package com.security.jwt.repository;

import com.security.jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.security.jwt.repository
 * @author: nhanph
 * @date: 2/10/2025 2025
 * @Copyright: @nhanph
 */

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}