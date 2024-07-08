package com.auth.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.auth.Constants;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    @Pattern(regexp = "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]*$", message = Constants.INVALID_JWT_TOKEN_FORMAT)
    private String token;
}
