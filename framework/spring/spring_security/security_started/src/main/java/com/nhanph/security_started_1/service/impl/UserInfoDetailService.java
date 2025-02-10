package com.nhanph.security_started_1.service.impl;

import com.nhanph.security_started_1.entity.User;
import com.nhanph.security_started_1.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @Package: com.nhanph.security_started_1.service.impl
 * @author: nhanph
 * @date: 2/4/2025 2025
 * @Copyright: @nhanph
 */
public class UserInfoDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoDetailService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userInfoRepository.findByUsername(username);
        if (userInfo.isPresent()) {
            return userInfo.get();
        }
        throw new UsernameNotFoundException(username);
    }
}
