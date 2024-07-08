package com.auth.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.auth.Constants;

@Component
@ConfigurationProperties(prefix="security.jwt")
@Getter
@Setter
@Validated
public class JWTProps {
    @Size(min = 60, message = Constants.SECRET_KEY_MUST_BE_AT_LEAST_60_CHARACTERS_LONG)
    private String secretKey;

    @Min(value = 10, message = Constants.REFRESH_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_10_MINUTES_LONG)
    private long refreshTokenExpirationMinutes;

    @Min(value = 10, message = Constants.ACCESS_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_10_MINUTES_LONG)
    private long accessTokenExpirationMinutes;

    @Min(value = 1, message = Constants.AUTH_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_1_MINUTES_LONG)
    private long authTokenExpirationMinutes;

    @Min(value = 5, message = Constants.REGISTER_TOKEN_EXPIRATION_MINUTE_MUST_BE_AT_LEAST_5_MINUTES_LONG)
    private long registerTokenExpirationMinutes;

    @NotBlank
    private String issuer;
}
