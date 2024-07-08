package com.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.auth.Constants;

@Data
@NoArgsConstructor
public class VerifyDto {

    private String token;
    @NotBlank(message = Constants.VERIFY_CODE_REQUIRED)
    @Pattern(regexp = "\\d+", message = Constants.VERIFY_CODE_MUST_BE_NUMBER)
    @Size(max=30)
    String number;
}
