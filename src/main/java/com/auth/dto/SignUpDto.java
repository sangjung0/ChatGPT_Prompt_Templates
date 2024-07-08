package com.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.auth.Constants;

@Data
@NoArgsConstructor
public class SignUpDto {
    @NotBlank(message = Constants.EMAIL_REQUIRED)
    @Email(message = Constants.EMAIL_INVALID)
    @Size(min = 5, max = 254, message = Constants.EMAIL_SIZE)
    private String email;

    @NotBlank(message=Constants.PASSWORD_REQUIRED)
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$", message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NotBlank(message=Constants.EMAIL_TOKEN_REQUIRED)
    private String emailToken;
}
