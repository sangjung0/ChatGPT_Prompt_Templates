package com.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.auth.Constants;

import com.auth.exception.VerifyException;
import com.auth.Util;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final VerifyTokenProvider verifyTokenProvider;

    public void sendEmail(String email, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        this.javaMailSender.send(message);
    }

    public String sendVerifyNumberByEmail(String email) throws MessagingException {
        String randomNum = Util.getRandomNum();
        sendEmail(
                email,Constants.EMAIL_SUBJECT,
                String.format(Constants.EMAIL_MESSAGE, randomNum)
        );
        return this.verifyTokenProvider.getPreAuthenticationToken(email, randomNum);
    }

    public String verify(String token, String number) throws VerifyException{
        VerifyTokenProvider.VerifyData authData = this.verifyTokenProvider.getPreAuthenticationTokenData(token);
        if(number.equals(authData.getVerifyNumber())){
            return this.verifyTokenProvider.getPostAuthenticationToken(authData.getAuthDetails());
        }
        throw new VerifyException(Constants.VERIFY_CODE_DOSE_NOT_MACH);
    }

    public String getEmailByToken(String token) throws VerifyException{
        VerifyTokenProvider.VerifyData authData = this.verifyTokenProvider.getPostAuthenticationTokenData(token);
        if(authData.getAuthType().equals(VerifyTokenProvider.AuthType.PreAuth.toString())){
            throw new VerifyException(Constants.UNAUTHENTICATED_EMAIL);
        }
        return authData.getAuthDetails();
    }
}