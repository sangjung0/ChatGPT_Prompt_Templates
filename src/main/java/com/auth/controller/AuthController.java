package com.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.auth.service.UserService;
import com.auth.dto.SignInDto;
import com.auth.dto.SignUpDto;
import com.auth.exception.CheckError;
import com.auth.exception.ValidException;
import com.auth.service.RefreshAccessTokenProvider;
import com.auth.service.EmailService;
import com.auth.Constants;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<?> authSignup(@RequestBody @Valid SignUpDto dt, BindingResult bindingResult){
        return AuthControllerBase.run(bindingResult,()->{

            String email = dt.getEmail();
            String password = dt.getPassword();

            if (!email.equals(emailService.getEmailByToken(dt.getEmailToken()))){
                return new ResponseEntity<String>(Constants.SIGNUP_EMAIL_DIFFERENT_ERROR, HttpStatus.BAD_REQUEST);
            }
            this.userService.registerUser(email, password);
            return new ResponseEntity<>(HttpStatus.OK);
        });
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid SignInDto dt, BindingResult bindingResult){
        return AuthControllerBase.run(bindingResult, () -> {
            RefreshAccessTokenProvider.TokenInfo tokenInfo = this.userService.logIn(dt.getEmail(), dt.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put(Constants.ACCESS_TOKEN, tokenInfo.getAccessToken());
            response.put(Constants.REFRESH_TOKEN, tokenInfo.getRefreshToken());
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

    private static class AuthControllerBase {
        public static ResponseEntity<?> run(BindingResult bindingResult,ControllerBase base){
            try{
                CheckError.checkValidException(bindingResult);
                return base.run();
            }catch(ValidException e){
                return new ResponseEntity<Map<String, String>>(e.getErrors(), HttpStatus.BAD_REQUEST);
            }catch(Exception e){
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }
}