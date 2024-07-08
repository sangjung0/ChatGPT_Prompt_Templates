package com.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.auth.Constants;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    @NotBlank(message = Constants.EMAIL_REQUIRED)
    @Email(message = Constants.EMAIL_INVALID)
    @Size(min = 5, max = 254, message = Constants.EMAIL_SIZE)
    private String email;
}

