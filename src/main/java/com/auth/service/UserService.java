package com.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.Constants;
import com.auth.User;
import com.auth.UserRepository;
import com.auth.exception.EmailException;
import com.auth.exception.LogInException;
import com.auth.exception.VerifyException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RefreshAccessTokenProvider refreshAccessTokenProvider;

    public void registerUser(String email, String password) throws EmailException {
        password = this.passwordEncoder.encode(password);

        Optional<User> found = this.userRepo.findByEmail(email);
        if(found.isPresent()) throw new EmailException(Constants.SIGNUP_EMAIL_EXIST);

        this.userRepo.save(new User(email, password));
    }

    public RefreshAccessTokenProvider.TokenInfo logIn(String email, String password) throws LogInException{
        password = this.passwordEncoder.encode(password);
        //log.info("{} {} 로그인 시도",email, password);

        Optional<User> found = this.userRepo.findByEmail(email);
        if(found.isEmpty() || !this.passwordEncoder.matches(password, found.get().getPassword())) throw new LogInException("일치하는 회원정보 없음");

        return this.refreshAccessTokenProvider.getToken(email);
    }

    public User getAuthUserFromSecurityContextHolder() throws VerifyException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> found = this.userRepo.findByEmail(auth.getName());
        if(found.isEmpty()) throw new VerifyException(Constants.BAD_REQUEST);
        return found.get();
    }


}
