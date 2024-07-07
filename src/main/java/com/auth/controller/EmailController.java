package com.auth.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.auth.dto.EmailDto;
import com.auth.dto.TokenDto;
import com.auth.dto.VerifyDto;
import com.auth.exception.CheckError;
import com.auth.exception.ValidException;
import com.auth.service.EmailService;

import java.util.Map;

@RestController
@RequestMapping("/api/verify/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> verifyEmail(@RequestBody @Valid EmailDto dt, BindingResult bindingResult){
        try{
            CheckError.checkValidException(bindingResult);
            return new ResponseEntity<TokenDto>(
                    new TokenDto(emailService.sendVerifyNumberByEmail(dt.getEmail())),
                    HttpStatus.OK
            );
        }catch(ValidException e){
            return new ResponseEntity<Map<String, String>>(e.getErrors(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/number")
    public ResponseEntity<?> verifyEmailNumber(@RequestBody @Valid VerifyDto dt, BindingResult bindingResult){
        try{
            CheckError.checkValidException(bindingResult);
            return new ResponseEntity<TokenDto>(
                    new TokenDto(emailService.verify(dt.getToken(), dt.getNumber())),
                    HttpStatus.OK
            );
        }catch (ExpiredJwtException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
