package com.auth.service;

import com.auth.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.User;
import com.auth.UserRepository;

@Order(2)
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user_ = userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
        return new UserDetailsImpl(user_);
    }
}
